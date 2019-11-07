package com.donum.accountservice.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.donum.accountservice.Controller.APIKeyController;
import com.donum.accountservice.Enum.EmailFields;
import com.donum.accountservice.Enum.Template_Paths;
import com.donum.accountservice.InternalService.JWTService;
import com.donum.accountservice.InternalService.GoogleApiServiceHelper;
import com.donum.accountservice.Model.MailRequest;
import com.donum.accountservice.Model.User;
import com.donum.accountservice.Service.EmailService;
import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;

import javax.validation.constraints.Email;

public class DynamoRepo {

    private EmailService emailService = new EmailService();

    private JWTService jwt = new JWTService();

    private final static Logger logger = Logger.getLogger(DynamoRepo.class);
    private BCryptPasswordEncoder _bCryptPasswordEncoder = new BCryptPasswordEncoder();


    public int createUser(User user){

        User userExists = APIKeyController._singleDynamoMapper.load(User.class, user.getEmail());

        if(userExists != null){
            System.out.println("User " + userExists.toString() + "Already exists!");
            return 1;
        }

        String randID = UUID.randomUUID().toString();
        user.setId(randID);

        try {
            String[] LatLong = GoogleApiServiceHelper.convertToCoordinates(user.getAddressline(), user.getPostcode());

            // location could not be determined
            if(LatLong[0].isEmpty() || LatLong[1].isEmpty()){
                user.setLatitude("500");
                user.setLongitude("500");

                APIKeyController._singleDynamoMapper.save(user);
            }

            user.setLatitude(LatLong[0]);
            user.setLongitude(LatLong[1]);

            APIKeyController._singleDynamoMapper.save(user);

        }catch (Exception e){

            // 500 error recorded, user can still register
            user.setLatitude("500");
            user.setLongitude("500");

            APIKeyController._singleDynamoMapper.save(user);
        }


        try {
            Map<String, Object> emailData = new HashMap<>();
            emailData.put("Name", user.getFirstName() + " " + user.getLastName());

            emailService.sendEmail(new MailRequest(randID, user.getFirstName(), user.getLastName(), EmailFields.FROM.toString(),
                            EmailFields.CONFIRMATION_SUBJECT.toString()), emailData, Template_Paths.EMAIL_CONFIRMATION.toString());
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
            emailService.sendEmail(new MailRequest(email, EmailFields.FROM.toString(),
                    EmailFields.RESET_SUBJECT.toString()), emailData, Template_Paths.RESET_PASSWORD.toString());
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
                updateUser.setPassword(update);
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
                if(_bCryptPasswordEncoder.matches(password, user.getPassword())){
                    return jwt.getJWT(email, _bCryptPasswordEncoder.encode(password));
                }
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return "Request Failed";
    }
}
