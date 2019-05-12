package com.bdonor.accountservice.InternalService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GoogleApiServiceHelper {

    public static String[] convertToCoordinates(String address, String postcode){

        String URLink;
        String[] LatLong = {};

        try {

            URL url = new URL(String.format("http://localhost:8000/google-api-service/geocoding/get-coordinates/%s/%s", address, postcode)); // Had to do this stuff here as i was "duplicate id" error in DynamoRepo
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
        return LatLong;
    }

}
