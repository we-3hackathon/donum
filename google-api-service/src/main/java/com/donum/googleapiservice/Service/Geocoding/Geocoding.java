package com.donum.googleapiservice.Service.Geocoding;

import com.donum.googleapiservice.Controller.GeoController;
import com.donum.googleapiservice.Enum.EnumAPIKey;
import com.donum.googleapiservice.Enum.EnumURL;
import com.donum.googleapiservice.Service.Json.Process;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Geocoding extends Thread{

    String _JSON = "E";
    String _address = "E";
    String _postcode = "E";
    String coordinates = "E";
    final static Logger logger = Logger.getLogger(Geocoding.class);


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

            HttpPost post = new HttpPost(_URL);

            HttpResponse response = client.execute(post);
            HttpEntity entity = response.getEntity();
            inputStream = entity.getContent();
        } catch(Exception e) {
            logger.error(e.getMessage());
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"),8);
            StringBuilder sbuild = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sbuild.append(line);
            }
            inputStream.close();
            _JSON = sbuild.toString();
        } catch(Exception e) {
            logger.error(e.getMessage());
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
