package com.bdonor.googleapiservice.Service;

import com.bdonor.googleapiservice.Model.Plot;
import com.bdonor.googleapiservice.Model.User;
import org.json.*;

import java.util.ArrayList;

public class ConvertJSON {


    public ArrayList<User> _usersData = new ArrayList<>();
    private String _JSONDATA = "";

    public ConvertJSON(String data){
        this._JSONDATA = data;
    }

    public void readAddData(){

        try {
            JSONObject obj = new JSONObject(_JSONDATA);

            JSONArray usersArray = obj.getJSONArray("users");

            for (int i = 0; i < usersArray.length(); i++) {
                String id = usersArray.getJSONObject(i).getString("id");
                String address = usersArray.getJSONObject(i).getString("address");
                String postcode = usersArray.getJSONObject(i).getString("postcode");
                String bloodgroup = usersArray.getJSONObject(i).getString("blood-group");

                _usersData.add(new Plot(postcode,address,bloodgroup));
            }

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public String readGetCoordinates(){

        String lat = "E_CONVERTJSON";
        String lon = "E_CONVERTJSON";
        try {
            JSONObject obj = new JSONObject(_JSONDATA);

            JSONArray usersArray = obj.getJSONArray("results");

            for (int i = 0; i < usersArray.length(); i++) {
                lat = usersArray.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").getString("lat");
                lon = usersArray.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").getString("lng");
            }

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        return lat + "," + lon;
    }

}
