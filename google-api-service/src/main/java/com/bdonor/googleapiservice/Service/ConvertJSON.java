package com.bdonor.googleapiservice.Service;

import com.bdonor.googleapiservice.Service.Plot;
import com.bdonor.googleapiservice.Model.User;
import org.json.*;

import java.util.ArrayList;

public class ConvertJSON {


    public ArrayList<User> _usersData = new ArrayList<>();
    private String _JSONDATA = "";
    private Plot mapPlot = Singleton.servicePlot;

    public ConvertJSON(String data){
        this._JSONDATA = data;
    }

    @Deprecated
    public void readAddData(){

        try {
            JSONObject obj = new JSONObject(_JSONDATA);

            JSONArray usersArray = obj.getJSONArray("users");

            for (int i = 0; i < usersArray.length(); i++) {
                String id = usersArray.getJSONObject(i).getString("id");
                String address = usersArray.getJSONObject(i).getString("address");
                String postcode = usersArray.getJSONObject(i).getString("postcode");
                String bloodgroup = usersArray.getJSONObject(i).getString("blood-group");

                //_usersData.add(new Plot(postcode,address,bloodgroup));
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

    public String processAllUsersJSON(){

        String lat = "";
        String lon = "";
        String bloodGroup = "";
        String color = "red";

        try {
            JSONArray allUsers = new JSONArray(_JSONDATA);



            for (int i = 0; i < allUsers.length(); i++) {
                lat = allUsers.getJSONObject(i).getString("_surname");
                lon = allUsers.getJSONObject(i).getString("_email");
                bloodGroup = allUsers.getJSONObject(i).getString("bloodGroup");

                System.out.println(lat);
                System.out.println(lon);
                System.out.println(bloodGroup);

                mapPlot.addMarker(mapPlot.setColour(bloodGroup),bloodGroup,lat,lon);
                //color = usersArray.getJSONObject(i).getString("latitude");
            }

            System.out.println("here is: " + mapPlot.getPlotURL());
            return mapPlot.getPlotURL();

        }catch (Exception ex){
            System.out.println(ex.getMessage());
            System.out.println("#1");
        }

        return lat + "," + lon;



    }

}
