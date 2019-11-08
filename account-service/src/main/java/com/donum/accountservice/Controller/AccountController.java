package com.donum.accountservice.Controller;

import com.donum.accountservice.Enum.EnumAPI_Links;
import com.donum.accountservice.Enum.ErrorMessage;
import com.donum.accountservice.Service.UsersInRange;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.donum.accountservice.Model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

@RestController
public class AccountController extends BaseController {

    @Autowired
    private UsersInRange _usersInRange;

    final static Logger logger = Logger.getLogger(AccountController.class);

    @ResponseBody
    @CrossOrigin()
    @PostMapping(value = "/accounts")
    public ResponseEntity<String> Register(@RequestBody User user) {

        switch (APIKeyController._singleDynamoRepo.createUser(user)) {
            case 1:
                return new ResponseEntity<>(ErrorMessage.EMAIL_INUSE.toString(), HttpStatus.CONFLICT);
            default:
                return new ResponseEntity<>(ErrorMessage.SUCCESS.toString(), HttpStatus.CREATED);
        }
    }

    @CrossOrigin()
    @PatchMapping(value = "/accounts")
    public ResponseEntity<String> updateUserAddress(@RequestBody String email, @RequestBody String update, @RequestBody int detail) {

        String message = "";

        switch (APIKeyController._singleDynamoRepo.updateUserDetail(email, detail, update)) {

            case 1:
                message = ErrorMessage.SUCCESS.toString();

                return new ResponseEntity<>(message, HttpStatus.OK);

            default:
                message = ErrorMessage.FAIL.toString();

                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin()
    @DeleteMapping(value = "/accounts")
    public ResponseEntity<String> deleteUserDetails(@RequestBody String email) {
        User user = new User();
        user.setEmail(email);
        if (APIKeyController._singleDynamoRepo.getSingleUser(email) != null) {
            APIKeyController._singleDynamoRepo.deleteUserDetails(user);
            return new ResponseEntity<>(user.toString() + " Deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>(ErrorMessage.USER_NOTFOUND.toString(), HttpStatus.NOT_FOUND);
    }

    @CrossOrigin()
    @GetMapping(value = "/accounts")
    public ResponseEntity<String> getUsers() {

        List<User> Users = APIKeyController._singleDynamoRepo.getAllUsers();
        if (Users.isEmpty()) {
            return new ResponseEntity<>(ErrorMessage.EMPTY.toString(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new Gson().toJson(Users), HttpStatus.OK);
    }

    @CrossOrigin()
    @PutMapping(value = "/accounts")
    public void updateUserDetails(@RequestBody User user) {
        APIKeyController._singleDynamoRepo.updateUserDetails(user);
    }

    @CrossOrigin()
    @PostMapping(value = "/accounts/{email}")
    public ResponseEntity<String> getUserDetails(@RequestBody String email) { // Working
        User user = APIKeyController._singleDynamoRepo.getSingleUser(email);
        if (user != null) {
            return new ResponseEntity<>(user.toString(), HttpStatus.OK);
        }
        return new ResponseEntity<>(ErrorMessage.USER_NOTFOUND.toString(), HttpStatus.NOT_FOUND);
    }

    @CrossOrigin()
    @PostMapping(value = "accounts/reset")
    public ResponseEntity<String> resetPasswordEmail(@RequestBody String email) {

        HttpStatus httpStatus = HttpStatus.OK;
        String message = "";

        // create a new reset token and send email
        switch (APIKeyController._singleDynamoRepo.passwordResetEmail(email)) {
            case 1:
                message = "Email Sent.";
                break;
            case -1:
                httpStatus = HttpStatus.BAD_REQUEST;
                message = "Error Encountered, Email Not Sent!";
        }
        return new ResponseEntity<>(message, httpStatus);
    }

    @CrossOrigin()
    @PatchMapping(value = "/accounts/reset")
    public ResponseEntity<String> resetPassword(@RequestBody String update, @RequestBody String email) {

        HttpStatus httpStatus = HttpStatus.OK;
        String message = "";

        // change the password in the database
        try {
            User user = APIKeyController._singleDynamoRepo.getSingleUser(email);
            if (!user.getResetPasswordToken().equals("")) {
                switch (APIKeyController._singleDynamoRepo.updateUserDetail(email, 1, update)) {
                    case 1:
                        APIKeyController._singleDynamoRepo.updateUserDetail(email, 5, "");
                        message = "Password Reset";
                    case -1:
                        httpStatus = HttpStatus.BAD_REQUEST;
                        message = "Reset Failed";
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(ErrorMessage.USER_NOTFOUND.toString(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, httpStatus);
    }

    @CrossOrigin()
    @GetMapping(value = "accounts/range/{longitude}/{latitude}/{radius}")
    public ResponseEntity<String> UsersInRangeOfRadius(@PathVariable double longitude, @PathVariable double latitude, @PathVariable int radius) {
        if (_usersInRange.getRadiusPostcodes(longitude, latitude, radius) != "") {
            return new ResponseEntity<>(_usersInRange.getRadiusPostcodes(longitude, latitude, radius), HttpStatus.OK);
        }
        return new ResponseEntity<>("No users in given radius", HttpStatus.NOT_FOUND);
    }

    @CrossOrigin()
    @PostMapping("/verify/{accesscode}/{email}")
    public ResponseEntity<String> Verify(@RequestBody User user) {

        try {
            User userInDB = APIKeyController._singleDynamoRepo.getSingleUser(user.getEmail());
            if (userInDB.isVerified()) {
                return new ResponseEntity<>("Already Verified", HttpStatus.CONFLICT);
            }
            if (userInDB.getAccesscode().equals(user.getAccesscode())) {
                APIKeyController._singleDynamoRepo.updateUserDetail(user.getEmail(), 4, "");
                return new ResponseEntity<>(user.getAccesscode(), HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(ErrorMessage.USER_NOTFOUND.toString(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Invalid!", HttpStatus.UNAUTHORIZED);
    }

    /***
     *
     * TODO: Possible change to a new controller?
     */
    @CrossOrigin()
    @GetMapping(value = "/login/{email}/{password}")
    public ResponseEntity<String> Login(@PathVariable String email, @PathVariable String password) {
        String token = APIKeyController._singleDynamoRepo.checkCredentials(email, password);
        if (!token.equals("Request Failed")) {
            try {
                return new ResponseEntity<>(token, HttpStatus.OK);
            } catch (Exception e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(ErrorMessage.FAIL.toString(), HttpStatus.UNAUTHORIZED);
    }

    @Override
    public void loadController() {
        _controllerName = "AccountController ";
    }
}