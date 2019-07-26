package com.bdonor.accountservice.InternalService;
import com.bdonor.accountservice.Model.User;
import com.bdonor.accountservice.Repository.DynamoRepo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class UsersInRange {

    @Autowired
    private DynamoRepo dynamoRepo;

    public String getRadiusPostcodes(float longitude, float latitude, int radius){

        StringBuffer Postcodes = new StringBuffer();
        List<User> allUsers = dynamoRepo.getAllUsers();
        ArrayList<JSONObject> returnedUsers = new ArrayList<>();

        try {

            URL url = new URL(String.format("https://api.postcodes.io/postcodes?lon=%slat=%sradius=%s",longitude,latitude,radius)); // Get Long and Lat
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
                Postcodes.append(output);
            }

            conn.disconnect();

            try {

                JSONObject jsonObject = new JSONObject(Postcodes.toString());
                JSONArray Results = jsonObject.getJSONArray("result");

                for(User Users: allUsers) {
                    for(int j = 0; j < Results.length(); j++ ){
                        if(Users.getPostcode().equals(Results.getJSONObject(j).getString("postcode"))){
                            returnedUsers.add(Results.getJSONObject(j));
                        }
                    }
                }

            } catch (Exception e) {
                System.out.println(e);
            }

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
        }
        return new Gson().toJson(returnedUsers);
    }

//    public static String returnUsersInRange(){
//
//        String URLink;
//        String[] LatLongRadius = {};
//
//        List<User> allUsers = dynamoRepo.getAllUsers();
//        ArrayList<JSONObject> returnedUsers = new ArrayList<>();
//
//        try {
//
//            URL url = new URL(String.format("http://localhost:8000/google-api-service/geocoding/get-coordinates/%s/%s")); // get long lat from sumites side
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//            conn.setRequestProperty("Accept", "application/String");
//
//            if (conn.getResponseCode() != 200) {
//                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
//            }
//
//            String output = "E";
//
//            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
//
//            System.out.println("Output from Server .... \n");
//
//            while ((output = br.readLine()) != null) {
//                URLink = output;
//                System.out.println(URLink);
//                LatLongRadius = URLink.split(",");
//            }
//
//            conn.disconnect();
//
//            try {
//
//                JSONArray Postcodes = getRadiusPostcodes(Float.valueOf(LatLongRadius[0]), Float.valueOf(LatLongRadius[1]));
//
//                for(User Users: allUsers) {
//                    for(int j = 0; j < Postcodes.length(); j++ ){
//                        if(Users.getPostcode().equals(Postcodes.getJSONObject(j).getString("postcode"))){
//                            returnedUsers.add(Postcodes.getJSONObject(j));
//                        }
//                    }
//                }
//
//            } catch (Exception e){
//                System.out.println(e);
//            }
//
//        } catch (MalformedURLException e) {
//
//            e.printStackTrace();
//
//        } catch (IOException e) {
//
//            e.printStackTrace();
//        }
//        return new Gson().toJson(returnedUsers);
//    }



}
