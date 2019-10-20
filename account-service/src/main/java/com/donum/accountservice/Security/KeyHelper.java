package com.donum.accountservice.Security;

import com.donum.accountservice.Enum.EnumAPIKey;
import com.donum.accountservice.Enum.EnumAPI_Links;
import com.donum.accountservice.Enum.Enum_Login_Credentials;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class KeyHelper {

    final static Logger logger = Logger.getLogger(KeyHelper.class);

    public String loadDynamoAccessKey() {

        return openConnectionToServer("dynamo/key");
    }

    public String loadDynamoSecretKey() {

        return openConnectionToServer("dynamo/secret-key");
    }

    public String loadEmailPassword() {

        return openConnectionToServer("gmail/password");
    }
	
    private String openConnectionToServer(String path){
        String jsonData = "error";

        try {

           URL url = new URL(EnumAPI_Links.API_KEYS + path); // special permissions set

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
				case "gmail/password":
					Enum_Login_Credentials.GMAIL_PASSWORD.updateKeyWith(jsonData);
					return Enum_Login_Credentials.GMAIL_PASSWORD.toString();
            }

        } catch (MalformedURLException e) {

            logger.error(e.getMessage());
            e.printStackTrace();

        } catch (IOException e) {

            logger.error(e.getMessage());
            e.printStackTrace();

        }
        return "Connection to server not made";
    }
}
