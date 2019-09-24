package com.donum.accountservice.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.donum.accountservice.Controller.APIKeyController;
import com.donum.accountservice.Enum.Template_Paths;
import com.donum.accountservice.InternalService.JWTService;
import com.donum.accountservice.InternalService.GoogleApiServiceHelper;
import com.donum.accountservice.Model.MailRequest;
import com.donum.accountservice.Model.User;
import com.donum.accountservice.Service.EmailService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;

public class DynamoRepo {

    private EmailService emailService = new EmailService();

    @Autowired
    private JWTService jwt;

    private final static Logger logger = Logger.getLogger(DynamoRepo.class);

	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public int createUser(String bloodGroup, String firstName, String lastName, String email, String password, String addressline, String postcode){

        User userExists = APIKeyController._singleDynamoMapper.load(User.class, email);

        if(userExists != null){
            System.out.println("User " + userExists.toString() + "Already exists!");
            return 1;
        }

        String[] LatLong = GoogleApiServiceHelper.convertToCoordinates(addressline,postcode);

        if (LatLong[0].contains("Not Found") || LatLong[1].contains("Not Found")){ return 2; }

        String randID = UUID.randomUUID().toString();

        APIKeyController._singleDynamoMapper.save(new User(randID, bloodGroup, firstName, lastName, email, bCryptPasswordEncoder.encode(password),
                addressline, postcode, LatLong[0], LatLong[1], false, ""));

        try {
            Map<String, Object> emailData = new HashMap<>();
            emailData.put("Name", firstName + " " + lastName);

            emailService.sendEmail(new MailRequest(randID, firstName, email, "Aroundhackathon@gmail.com",
                            "Email Confirmation"), emailData, Template_Paths.EMAIL_CONFIRMATION.toString());
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public List<User> getAllUsers(){
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        return APIKeyController._singleDynamoMapper.scan(User.class, scanExpression);
    }

    public User getSingleUser(String email) {
        return APIKeyController._singleDynamoMapper.load(User.class, email);
    }

    public void updateUserDetails(User user) {
        try {
            APIKeyController._singleDynamoMapper.save(user, buildDynamoDBSaveExpression(user));
        } catch (ConditionalCheckFailedException exception) {
            logger.error(exception.getMessage());
            exception.printStackTrace();
        }
    }

    public int passwordResetEmail(String email) {
        try {
            Map<String, Object> emailData = new HashMap<>();

            String ResetToken = UUID.randomUUID().toString();

            if(getSingleUser(email) != null) {
                updateUserDetail(email, 5, ResetToken);
            }
            emailData.put("ResetURL", "http://localhost:3000/resetpassword" + ResetToken + "/" + email);
            emailService.sendEmail(new MailRequest(email, "Aroundhackathon@gmail.com",
                    "Reset Password"), emailData, Template_Paths.RESET_PASSWORD.toString());
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public int updateUserDetail(String email, int detail, String update){

        User userInDB = APIKeyController._singleDynamoMapper.load(User.class, email);
        User updateUser = APIKeyController._singleDynamoMapper.load(User.class, email);

        switch(detail){
            case 1:
                updateUser.setPassword(bCryptPasswordEncoder.encode(update));
                break;
            case 2:
                String[] addressPostcode = update.split("|");
                updateUser.setAddressline(addressPostcode[0]);
                updateUser.setPostcode(addressPostcode[1]);
                break;
            case 3:
                updateUser.setEmail(update);
            case 4:
                updateUser.setVerified(true);
            case 5:
                updateUser.setResetPasswordToken(update);
                break;
        }
        try {
            APIKeyController._singleDynamoMapper.save(updateUser, buildDynamoDBSaveExpression(userInDB));
        } catch (ConditionalCheckFailedException exception) {
            logger.error(exception.getMessage());
            exception.printStackTrace();
            return -1;
        }
        return 1;
    }

    public DynamoDBSaveExpression buildDynamoDBSaveExpression(User user) {

        DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression();
        Map<String, ExpectedAttributeValue> expected = new HashMap<>();
        expected.put("Email", new ExpectedAttributeValue(new AttributeValue(user.getEmail())).withComparisonOperator(ComparisonOperator.EQ));  // Checks against the given users ID
        saveExpression.setExpected(expected);
        return saveExpression;
    }

    public void deleteUserDetails(User user) {
        APIKeyController._singleDynamoMapper.delete(user);
    }

    public String checkCredentials(String email, String password){
        try{
            User user = APIKeyController._singleDynamoMapper.load(User.class, email);
            if(user != null && user.isVerified()){
                if(bCryptPasswordEncoder.matches(password, user.getPassword())){
                    return jwt.getJWT(email, bCryptPasswordEncoder.encode(password));
                }
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return "Request Failed";
    }
}
