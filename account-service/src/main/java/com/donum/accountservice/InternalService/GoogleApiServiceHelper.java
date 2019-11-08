package com.donum.accountservice.InternalService;

import com.donum.accountservice.Enum.EnumAPI_Links;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GoogleApiServiceHelper {

    final static Logger logger = Logger.getLogger(GoogleApiServiceHelper.class);

    public static String[] convertToCoordinates(String address, String postcode){

        String URLink;
        String[] LatLong = {};

        try {

            URL url = new URL(String.format(EnumAPI_Links.GOOGLE_API + "geocoding/get-coordinates/%s/%s", address, postcode));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/String");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            String output = "E";
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            System.out.println("Output from Server .... \n");

            // some defensive coding required here in cases:
            // 1 - Response has "Not found" -> allow save to database with 'null' latitudes and longitudes
            // 2 - Response has "API not set" -> save to database not permitted
            // 3 - Any other errors
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
