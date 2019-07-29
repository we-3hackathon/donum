package com.bdonor.accountservice.Controller;

import com.amazonaws.services.dynamodbv2.xspec.S;
import com.bdonor.accountservice.InternalService.UsersInRange;
import com.bdonor.accountservice.Model.User;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController extends BaseController{

    @Autowired
    private UsersInRange _usersInRange;

    @ResponseBody
    @GetMapping(value = "/create/{bloodGroup}/{firstName}/{lastName}/{email}/{password}/{addressline}/{postcode}") // Need to test once google-api-serivce is merged
    public String Register( @PathVariable String bloodGroup , @PathVariable  String firstName, @PathVariable  String lastName, @PathVariable  String email, @PathVariable  String password, @PathVariable  String addressline, @PathVariable  String postcode){

        switch (APIKeyController._singleDynamoRepo.createUser(bloodGroup, firstName, lastName, email, password, addressline, postcode)){

            case 1:
                return "Email in use. Try another email";
            case 2:
                return "Postcode not recognised";
            default:
                return "User added to Database";
        }
    }

    @GetMapping(value = "/get-all") // Working
    public String getUsers() {
        String Users = new Gson().toJson(APIKeyController._singleDynamoRepo.getAllUsers());
        return Users;
    }

    @GetMapping(value = "/getUser/{firstName}/{email}")
    public String getUserDetails(@PathVariable String firstName, @PathVariable String email) { // Working
        User user = APIKeyController._singleDynamoRepo.getSingleUser(firstName, email);
        if(user != null){
            return user.toString();
        }
        return "User not found";
    }

    @PutMapping(value = "/updateUser")
    public void updateUserDetails(@RequestBody User user) {
        APIKeyController._singleDynamoRepo.updateUserDetails(user);
    }

    @DeleteMapping(value = "/delete/{firstName}/{email}")
    public String deleteUserDetails(@PathVariable String firstName, @PathVariable String email) { // Working
        User user = new User();
        user.setFirstName(firstName);
        user.setEmail(email);
        if(APIKeyController._singleDynamoRepo.getSingleUser(firstName, email) != null){
            APIKeyController._singleDynamoRepo.deleteUserDetails(user);
            return user.toString() + " Deleted";
        }
        return "User not found";
    }

    @GetMapping(value = "/login/{firstName}/{email}/{password}")
    public String Login(@PathVariable String firstName, @PathVariable String email, @PathVariable String password) { // Working
        if(APIKeyController._singleDynamoRepo.checkCredentials(firstName, email, password)){
            try {
                JSONObject User = new JSONObject(new Gson().toJson(APIKeyController._singleDynamoRepo.getSingleUser(firstName, email)));
                User.remove("password");
                return new Gson().toJson(User);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "Login Failed";
    }

    @GetMapping(value = "/usersInRange/{longitude}/{latitude}/{radius}")
    public String UsersInRangeOfRadius(@PathVariable double longitude, @PathVariable double latitude, @PathVariable int radius){
        if(_usersInRange.getRadiusPostcodes(longitude, latitude, radius) != ""){
            return _usersInRange.getRadiusPostcodes(longitude, latitude, radius);
        }
        return "No users in given radius";
    }

    @Override
    public void loadController() {
        _controllerName = "AccountController ";
    }
}
