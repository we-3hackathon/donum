package com.bdonor.googleapiservice.InternalService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AccountServiceHelper {

    public static String requestAllUsers(){

        String jsonData = "error";

        try {

            URL url = new URL("http://localhost:8000/account-service/getAll");
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
            return jsonData;

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return jsonData;

    }
}
