package com.bdonor.accountservice.Controller;

import com.bdonor.accountservice.Model.User;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController extends BaseController{

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
    public ResponseEntity<User> getUserDetails(@PathVariable String firstName, @PathVariable String email) { // Working
        User user = APIKeyController._singleDynamoRepo.getSingleUser(firstName, email);
        System.out.println(user.toString());
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PutMapping(value = "/updateUser")
    public void updateUserDetails(@RequestBody User user) { // Working -- Not sure how i will re-encrypt new users password
        APIKeyController._singleDynamoRepo.updateUserDetails(user);
    }

    @DeleteMapping(value = "/delete/{firstName}/{email}")
    public void deleteUserDetails(@PathVariable String firstName, @PathVariable String email) { // Working
        User user = new User();
        user.setFirstName(firstName);
        user.setEmail(email);
        APIKeyController._singleDynamoRepo.deleteUserDetails(user);
        System.out.println(user.toString() + " Deleted");
    }

    @GetMapping(value = "/login/{firstName}/{email}/{password}")
    public String Login(@PathVariable String firstName, @PathVariable String email, @PathVariable String password) { // Working
        if(APIKeyController._singleDynamoRepo.checkCredentials(firstName, email, password)){
            return new Gson().toJson(APIKeyController._singleDynamoRepo.getSingleUser(firstName, email));
        }
        return "Login Failed";
    }

    @Override
    public void loadController() {
        _controllerName = "AccountController ";
    }
}
