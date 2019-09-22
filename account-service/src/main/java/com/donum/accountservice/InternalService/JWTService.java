package com.donum.accountservice.InternalService;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class JWTService {

    public String getJWT(String email, String password){
        String token = "";
        try {
            URL url = new URL ("http://127.0.0.1:5000/token-creation/user");

            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setDoOutput(true);

            String jsonString = new JSONObject()
                    .put("email", email).put("password", password).toString();

            try(OutputStream os = con.getOutputStream()) {
                byte[] input =  jsonString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "utf-8"))) {
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    token = responseLine.trim();
                }
                System.out.println(token);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(token);
        return token;
    }
}
