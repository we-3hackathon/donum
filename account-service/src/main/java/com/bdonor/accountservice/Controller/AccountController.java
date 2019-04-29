package com.bdonor.accountservice.Controller;

import com.bdonor.accountservice.Models.User;
import com.bdonor.accountservice.Repository.DynamoRepo;
//import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class AccountController {

    @Autowired
    private DynamoRepo dynamoRepo;

    @ResponseBody
    @PostMapping(value = "/create/{bloodGroup}/{firstname}/{surname}/{email}/{password}/{addressline}/{postcode}")
    public String Register( @PathVariable String bloodGroup , @PathVariable  String firstname, @PathVariable  String surname, @PathVariable  String email, @PathVariable  String password, @PathVariable  String addressline, @PathVariable  String postcode){
        dynamoRepo.createUserTest(bloodGroup, firstname,  surname,  email,  password,  addressline,  postcode);
        return "User added to Dynamo Database";
    }

    @GetMapping(value = "/getUser/{firstName}/{email}")
    public ResponseEntity<User> getUserDetails(@RequestParam String firstName, @RequestParam String email) {
        User user = dynamoRepo.getSingleUser(firstName, email); // Hash Key and Range Key
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PutMapping(value = "/updateUser")
    public void updateUserDetails(@RequestBody User user) {
        dynamoRepo.updateUserDetails(user);
    }

    @DeleteMapping(value = "/delete/{firstName}/{email}")
    public void deleteUserDetails(@PathVariable String firstName, @PathVariable String email) {
        User user = new User();
        user.set_id(firstName);
        user.setEmail(email);
        dynamoRepo.deleteUserDetails(user);
        System.out.println(user.toString() + "Deleted");
    }

    @GetMapping(value = "/login/{firstName}/{email}/{password}")
    public String Login(@PathVariable String firstName, @PathVariable String email, @PathVariable String password) { // Works
        System.out.println("runnign");
        if(dynamoRepo.checkCredentials(firstName, email, password)){
            return "Login Successful";
        }
        return "Login Failed";
    }

}
