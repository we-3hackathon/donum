package com.bdonor.accountservice.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.bdonor.accountservice.Controller.APIKeyController;
import com.bdonor.accountservice.InternalService.GoogleApiServiceHelper;
import com.bdonor.accountservice.Model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;

public class DynamoRepo {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamoRepo.class);
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public int createUser(String bloodGroup, String firstName, String lastName, String email, String password, String addressline, String postcode){
        User checkEmail = APIKeyController._singleDynamoMapper.load(User.class, firstName, email);
        if (checkEmail != null) {
            System.out.println("User " + checkEmail.toString() + "Already exists!");
            return 1;
        }

        String[] LatLong = GoogleApiServiceHelper.convertToCoordinates(addressline,postcode);

        if (LatLong[0].contains("Not Found") || LatLong[1].contains("Not Found")){return 2;}

        APIKeyController._singleDynamoMapper.save(new User(bloodGroup, firstName, lastName, email, bCryptPasswordEncoder.encode(password), addressline, postcode, LatLong[0], LatLong[1]));
        return 0;
    }

    public List<User> getAllUsers(){
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        List<User> scanResult = APIKeyController._singleDynamoMapper.scan(User.class, scanExpression);
        return scanResult;
    }

    public User getSingleUser(String firstName, String email) {
        return APIKeyController._singleDynamoMapper.load(User.class, firstName, email);
    }

    public void updateUserDetails(User user) {
        try {
            APIKeyController._singleDynamoMapper.save(user, buildDynamoDBSaveExpression(user));
        } catch (ConditionalCheckFailedException exception) {
            LOGGER.error("invalid data - " + exception.getMessage());
        }
    }

    public void deleteUserDetails(User user) {
        APIKeyController._singleDynamoMapper.delete(user);
    }

    public DynamoDBSaveExpression buildDynamoDBSaveExpression(User user) {

        DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression();
        Map<String, ExpectedAttributeValue> expected = new HashMap<>();
        expected.put("firstName", new ExpectedAttributeValue(new AttributeValue(user.getFirstName())).withComparisonOperator(ComparisonOperator.EQ));  // Checks against the given users ID
        saveExpression.setExpected(expected);
        return saveExpression;
    }

    public boolean checkCredentials(String firstName, String email, String password){
        try{
            User user = APIKeyController._singleDynamoMapper.load(User.class, firstName, email);
            if(user != null){
                if(bCryptPasswordEncoder.matches(password, user.getPassword())){
                    return true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

}
