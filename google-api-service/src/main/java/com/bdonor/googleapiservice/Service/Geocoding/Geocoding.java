package com.bdonor.googleapiservice.Service.Geocoding;

import com.bdonor.googleapiservice.Strings.EnumAPIKey;
import com.bdonor.googleapiservice.Strings.EnumURL;
import com.bdonor.googleapiservice.Service.Json.Process;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Geocoding extends Thread{

    String _JSON = "E";
    String _address = "E";
    String _postcode = "E";
    String coordinates = "E";


     public Geocoding(String address, String postcode){
        this._address = address;
        this._postcode = postcode;
    }


    public void run(){
        InputStream inputStream = null;

        try {
            HttpClient client = HttpClientBuilder.create().build();

            String _URL = EnumURL.PROTOCOL.toString() + EnumURL.HOST.toString() + EnumURL.PATH.toString()
                    + EnumURL.ADDRESS.toString() + _address + "," + _postcode
                    + EnumURL.KEY.toString() + EnumAPIKey.PLACES_KEY.toString();

            System.out.println(_URL);
            HttpPost post = new HttpPost(_URL);

            HttpResponse response = client.execute(post);
            HttpEntity entity = response.getEntity();
            inputStream = entity.getContent();
        } catch(Exception e) {
            System.out.println("1");
            System.out.println(e.getMessage());
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"),8);
            StringBuilder sbuild = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sbuild.append(line);
            }
            inputStream.close();
            System.out.println(sbuild.toString());
            _JSON = sbuild.toString();
        } catch(Exception e) {
            System.out.println("2");
            System.out.println(e.getMessage());

        }

        Process ProcessThread = new Process(_JSON);

        ProcessThread.setState(2);
        ProcessThread.run();

        while(ProcessThread.isAlive()){}

        coordinates = ProcessThread.getOutcome();
    }

    public String getCoordinates() {
        return coordinates;
    }
}
