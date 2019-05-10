package com.bdonor.googleapiservice.Security;

import com.bdonor.googleapiservice.Model.Variable.EnumAPIKey;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class KeyHelper {

    public String loadGoogleMapApiKey() {

        return openConnectionToServer("google/map");
    }

    public String loadGooglePlacesApiKey() {

        return openConnectionToServer("google/geocoding");
    }

    private String openConnectionToServer(String path){
        String jsonData = "error";

        try {

            URL url = new URL("http://3.8.143.207:5444/get-key/"+ path); // special permissions set

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            //conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            String output = "E";
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                jsonData = output;
            }

            conn.disconnect();
            EnumAPIKey.MAP_KEY.updateKeyWith(jsonData);

            return EnumAPIKey.MAP_KEY.toString();

        } catch (
                MalformedURLException e) {

            e.printStackTrace();

        } catch (
                IOException e) {

            e.printStackTrace();

        }
        return "Connection to server not made";
    }
}
