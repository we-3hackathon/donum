package com.donum.accountservice.Security;

import com.donum.accountservice.Model.Variable.EnumAPIKey;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class KeyHelper {

    public String loadDynamoAccessKey() {

        return openConnectionToServer("dynamo/key");
    }

    public String loadDynamoSecretKey() {

        return openConnectionToServer("dynamo/secret-key");
    }

    private String openConnectionToServer(String path){
        String jsonData = "error";

        try {



           URL url = new URL("http://18.130.137.35:5333/get-key/" + path); // special permissions set

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


            switch (path){
                case "dynamo/key":
                    EnumAPIKey.DYNAMO_KEY.updateKeyWith(jsonData);
                    return EnumAPIKey.DYNAMO_KEY.toString();
                case "dynamo/secret-key":
                    EnumAPIKey.DYNAMO_SECRET_KEY.updateKeyWith(jsonData);
                    return EnumAPIKey.DYNAMO_SECRET_KEY.toString();
            }

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return "Connection to server not made";
    }
}
