package com.bdonor.accountservice.Controller;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.bdonor.accountservice.Models.User;
import com.bdonor.accountservice.Repository.DynamoRepo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


@RestController
public class AccountController {

    @Autowired
    private DynamoRepo dynamoRepo;

    @Autowired
    private DynamoDBMapper mapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @ResponseBody
    @PostMapping(value = "/create/{bloodGroup}/{firstName}/{lastName}/{email}/{password}/{addressline}/{postcode}")
    public String Register( @PathVariable String bloodGroup , @PathVariable  String firstName, @PathVariable  String lastName, @PathVariable  String email, @PathVariable  String password, @PathVariable  String addressline, @PathVariable  String postcode){
        User checkEmail = mapper.load(User.class, firstName, email);
        if (checkEmail != null) {
            System.out.println("User Exists");
            return "Email in use, try again with a different email";
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
