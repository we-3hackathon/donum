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

    @GetMapping(value = "/getUser/{_id}/{lastName}")
    public ResponseEntity<User> getUserDetails(@RequestParam String _id, @RequestParam String lastName) {
        User user = dynamoRepo.getSingleUser(_id, lastName);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PutMapping(value = "/updateUser")
    public void updateUserDetails(@RequestBody User user) {
        dynamoRepo.updateUserDetails(user);
    }

    @DeleteMapping(value = "/delete/{_id}/{email}")
    public void deleteUserDetails(@PathVariable String _id, @PathVariable String email) {
        User user = new User();
        user.set_id(_id);
        user.setEmail(email);
        dynamoRepo.deleteUserDetails(user);
        System.out.println(user.toString() + "Deleted");
    }

}
