package com.bdonor.accountservice.Controller;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.bdonor.accountservice.Models.User;
import com.bdonor.accountservice.Repository.DynamoRepo;
//import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
public class AccountController {

    @Autowired
    private DynamoRepo dynamoRepo;

    @Autowired
    private DynamoDBMapper mapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @ResponseBody
    @PostMapping(value = "/create/{bloodGroup}/{firstName}/{lastName}/{email}/{password}/{addressline}/{postcode}") // Working
    public String Register( @PathVariable String bloodGroup , @PathVariable  String firstName, @PathVariable  String lastName, @PathVariable  String email, @PathVariable  String password, @PathVariable  String addressline, @PathVariable  String postcode){
        mapper.save(new User(bloodGroup, firstName, lastName, email, bCryptPasswordEncoder.encode(password), addressline, postcode));
        return "User added to Dynamo Database";
    }

    @GetMapping(value = "/getUser/{firstName}/{email}")
    public ResponseEntity<User> getUserDetails(@PathVariable String firstName, @PathVariable String email) { // Working
        User user = dynamoRepo.getSingleUser(firstName, email);
        System.out.println(user.toString());
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PutMapping(value = "/updateUser")
    public void updateUserDetails(@RequestBody User user) { // Working -- Not sure how i will re-encrypt new users password
        dynamoRepo.updateUserDetails(user);
    }

    @DeleteMapping(value = "/delete/{firstName}/{email}")
    public void deleteUserDetails(@PathVariable String firstName, @PathVariable String email) { // Working
        User user = new User();
        user.setFirstName(firstName);
        user.setEmail(email);
        dynamoRepo.deleteUserDetails(user);
        System.out.println(user.toString() + "Deleted");
    }

    @GetMapping(value = "/login/{firstName}/{email}/{password}")
    public String Login(@PathVariable String firstName, @PathVariable String email, @PathVariable String password) { // Working
        System.out.println("runnign");
        if(dynamoRepo.checkCredentials(firstName, email, password)){
            return "Login Successful";
        }
        return "Login Failed";
    }

}
