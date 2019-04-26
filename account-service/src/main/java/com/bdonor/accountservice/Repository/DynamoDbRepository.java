package com.bdonor.accountservice.Repository;

import java.util.HashMap;
import java.util.Map;

import com.bdonor.accountservice.Models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;

@Repository
public class DynamoDbRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamoDbRepository.class);

    @Autowired
    private DynamoDBMapper mapper;

//    @Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder; // Autowiring this gives me an error for some reason, when it is not autowired, createUser does not work

    public void createUserTest(String bloodGroup, String firstName, String lastName, String email, String password, String addressline, String postcode) { // Test Function without google api service
        mapper.save(new User(bloodGroup, firstName, lastName, email, password, addressline, postcode));
    }

    public void createUser(String bloodGroup, String firstName, String lastName, String email, String password, String addressline, String postcode, String latitude, String longitude) { //  Official
        mapper.save(new User(bloodGroup, firstName, lastName, email, password, addressline, postcode, latitude, longitude));
    }


    public User getSingleUser(String _id, String email) {
        return mapper.load(User.class, _id, email);
    }

    public void updateUserDetails(User user) {
        try {
            mapper.save(user, buildDynamoDBSaveExpression(user));
        } catch (ConditionalCheckFailedException exception) {
            LOGGER.error("invalid data - " + exception.getMessage());
        }
    }

//	public boolean checkCredentials(String email, String password) {
//		try {
//			User user = repo.findByEmail(email);
//			//User user = UserRepo.findByEmail(email);
//			if (user != null) {
//				if (bCryptPasswordEncoder.matches(password,user.getPassword())) {
//					return true;
//				}
//			}
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//		return false;
//	}

    public void deleteUserDetails(User user) {
        mapper.delete(user);
    }

    public DynamoDBSaveExpression buildDynamoDBSaveExpression(User user) {

        DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression();

        Map<String, ExpectedAttributeValue> expected = new HashMap<>();

        expected.put("_id", new ExpectedAttributeValue(new AttributeValue(user.get_id())).withComparisonOperator(ComparisonOperator.EQ));
        saveExpression.setExpected(expected);
        return saveExpression;
    }

}
