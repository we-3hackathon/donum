package com.donum.googleapiservice.InternalService;

import com.donum.googleapiservice.Controller.GeoController;
import com.donum.googleapiservice.Enum.ConnectionStrings;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AccountServiceHelper {

    final static Logger logger = Logger.getLogger(AccountServiceHelper.class);


    public static String requestAllUsers(){

        String jsonData = "error";

        try {

            URL url = new URL(ConnectionStrings.ACCOUNT_SERVICE + "/get-all");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            String output = "E";
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            while ((output = br.readLine()) != null) {
                jsonData = output;
            }

            conn.disconnect();
            return jsonData;

        } catch (MalformedURLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            jsonData = e.getMessage();
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            jsonData = e.getMessage();
        }
        return jsonData;
    }
}
