//package com.bdonor.accountservice.Models;
//
//import com.bdonor.accountservice.Repository.DynamoDBRepo;
////import com.bdonor.accountservice.Repository.UserRepository;
////import com.bdonor.accountservice.Service.passwordEncoder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.List;
//
//@Service
//public class AccountHelper {
//
////    @Autowired
////    private UserRepository UserRepo;
//
//    private DynamoDBRepo dynamoDBRepo;
//
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    public User create(String bloodGroup, String firstname, String surname, String email, String password, String addressline, String postcode) { // This Works
////        User user = dynamoDBRepo.findByEmail(email);
//////        User user = UserRepo.findByEmail(email);
////        if (user != null) {
////            System.out.println("User Exists");
////            return new User();
////        }
//
////        String URLink;
////        String[] LatLong = {};
////
////        try {
////
////            URL url = new URL(String.format("http://localhost:8110/geocoding/%s/%s", addressline, postcode));
////            //System.out.println(url.toString());
////            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
////            conn.setRequestMethod("GET");
////            conn.setRequestProperty("Accept", "application/String");
////
////            if (conn.getResponseCode() != 200) {
////                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
////            }
////
////            String output = "E";
////            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
////
////            System.out.println("Output from Server .... \n");
////            while ((output = br.readLine()) != null) {
////                URLink = output;
////                System.out.println(URLink);
////                LatLong = URLink.split(",");
////            }
////
////            conn.disconnect();
////
////        } catch (MalformedURLException e) {
////
////            e.printStackTrace();
////
////        } catch (IOException e) {
////
////            e.printStackTrace();
////        }
//
//        return dynamoDBRepo.save(new User(bloodGroup, firstname, surname, email, bCryptPasswordEncoder.encode(password), addressline, postcode));
//
//        //return dynamoDBRepo.save(new User(bloodGroup, firstname, surname, email, bCryptPasswordEncoder.encode(password), addressline, postcode, LatLong[0], LatLong[1]));
//
//       // return UserRepo.save(new User(bloodGroup, firstname, surname, email, bCryptPasswordEncoder.encode(password), addressline, postcode, LatLong[0], LatLong[1]));
//
//    }
//
//
//    public User getByfirstName(String firstname) {
//
//        return dynamoDBRepo.findByFirstName(firstname);
//
//        //return UserRepo.findByFirstName(firstname);
//    }
//
////    public List<User> getAll() {
////        return dynamoDBRepo.findAll();
////        //return UserRepo.findAll();
////    }
//
//    public User Update(String bloodGroup, String firstname, String surname, String email, String password, String addressline, String postcode) {
//
//        //User SpecificUser = UserRepo.findByFirstName(firstname);
//        User SpecificUser = dynamoDBRepo.findByFirstName(firstname);
//        SpecificUser.setBloodGroup(bloodGroup);
//        SpecificUser.setFirstname(firstname);
//        SpecificUser.setSurname(surname);
//        SpecificUser.setEmail(email);
//        SpecificUser.setPassword(bCryptPasswordEncoder.encode(password));
//        SpecificUser.setAddressline(addressline);
//        SpecificUser.setPostcode(postcode);
//
//        return dynamoDBRepo.save(SpecificUser);
//
//        //return UserRepo.save(SpecificUser);
//
//    }
//
//    public void deleteAll() {
//
//        dynamoDBRepo.deleteAll();
//
//        //UserRepo.deleteAll();
//    }
//
//    public void deleteByfirstName(String firstname) {
//        //User user = UserRepo.findByFirstName(firstname);
//        User user = dynamoDBRepo.findByFirstName(firstname);
//        System.out.println(user);
//
//        dynamoDBRepo.delete(user);
//
//        //UserRepo.delete(user);
//    }
//
//    public boolean checkCredentials(String email, String password) {
//        try {
//            User user = dynamoDBRepo.findByEmail(email);
//            //User user = UserRepo.findByEmail(email);
//            if (user != null) {
//                if (bCryptPasswordEncoder.matches(password,user.getPassword())) {
//                    return true;
//                }
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return false;
//    }
//}