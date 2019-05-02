package com.bdonor.accountservice.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.bdonor.accountservice.Models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;

@Repository
public class DynamoRepo {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamoRepo.class);

    @Autowired
    private DynamoDBMapper mapper;

    @Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean createUser(String bloodGroup, String firstName, String lastName, String email, String password, String addressline, String postcode){
        User checkEmail = mapper.load(User.class, firstName, email);
        if (checkEmail != null) {
            System.out.println("User " + checkEmail.toString() + "Already exists!");
            return false;
        }

        String URLink;
        String[] LatLong = {};

        try {

            URL url = new URL(String.format("http://localhost:8110/geocoding/%s/%s", addressline, postcode)); // Had to do this stuff here as i was "duplicate id" error in DynamoRepo
            //System.out.println(url.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/String");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            String output = "E";
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                URLink = output;
                System.out.println(URLink);
                LatLong = URLink.split(",");
            }

            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
        }
        mapper.save(new User(bloodGroup, firstName, lastName, email, bCryptPasswordEncoder.encode(password), addressline, postcode, LatLong[0], LatLong[1]));
        return true;
    }

    public List<User> getAllUsers(){
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        List<User> scanResult = mapper.scan(User.class, scanExpression);
        return scanResult;
    }

    public User getSingleUser(String firstName, String email) {
        return mapper.load(User.class, firstName, email);
    }

    public void updateUserDetails(User user) {
        try {
            mapper.save(user, buildDynamoDBSaveExpression(user));
        } catch (ConditionalCheckFailedException exception) {
            LOGGER.error("invalid data - " + exception.getMessage());
        }
    }

    public void deleteUserDetails(User user) {
        mapper.delete(user);
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
            User user = mapper.load(User.class, firstName, email);
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
