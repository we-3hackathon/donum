package com.donum.accountservice.Service;
import com.donum.accountservice.Controller.APIKeyController;
import com.donum.accountservice.Model.User;
import com.donum.accountservice.Links.EnumAPI_Links;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsersInRange {

    public String getRadiusPostcodes(double longitude, double latitude, int radius){

        StringBuffer Postcodes = new StringBuffer();
        List<User> allUsers = APIKeyController._singleDynamoRepo.getAllUsers();
        ArrayList<User> returnedUsers = new ArrayList<>();

        try {

            URL url = new URL(String.format(EnumAPI_Links.POSTCODE_API + "postcodes?lon=%s?&lat=%s?&radius=%s" ,longitude,latitude,radius)); // Get Long and Lat
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

                System.out.println(Results);

                for(User Users: allUsers) {
                    for(int j = 0; j < Results.length(); j++ ){
                        if(Users.getPostcode().toLowerCase().equals(Results.getJSONObject(j).getString("postcode").toLowerCase().replaceAll(" ", ""))){
                            returnedUsers.add(Users);
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
}
