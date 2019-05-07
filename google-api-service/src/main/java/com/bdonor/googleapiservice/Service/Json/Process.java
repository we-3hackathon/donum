package com.bdonor.googleapiservice.Service.Json;

import com.bdonor.googleapiservice.Service.GoogleMap.SingletonPlot;
import org.json.*;

import java.util.ArrayList;

public class Process extends Thread{

    private String _JSONDATA = "";
    private SingletonPlot mapPlot = SingletonPlot.getInstance();
    private int _state = 0;
    private String _outcome = "Thread not complete";

    /**
     * @param data - the JSON string.
     */
    public Process(String data){
        this._JSONDATA = data;
    }

    /**
     * @Deprecated for now, possibly deleted; not in use.
     */
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

                //_usersData.add(new SingletonPlot(postcode,address,bloodgroup));
            }

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    /*
    Process the JSON from Google and retrieve the @latitude and @longitude
    only from the request
     */
    private String readGetCoordinates(){

        String lat = "Not Found";
        String lon = "Not Found";
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

    /**
     * Process the JSON received from account-service
     * and build the URL using GoogleMap.SingletonPlot service
     */
    private String processAllUsersJSON() {

        String lat = "";
        String lon = "";
        String bloodGroup = "";
        String color = "red";

        try {
            JSONArray allUsers = new JSONArray(_JSONDATA);

            for (int i = 0; i < allUsers.length(); i++) {
                lat = allUsers.getJSONObject(i).getString("latitude");
                lon = allUsers.getJSONObject(i).getString("longitude");
                bloodGroup = allUsers.getJSONObject(i).getString("bloodGroup");

                System.out.println(lat);
                System.out.println(lon);
                System.out.println(bloodGroup);

                /* defense against AB blood group,
                * google marker cannot support 2 or more characters in single plot
                * */
                if(bloodGroup.contains("AB")){
                    bloodGroup = "Z";
                }

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

    @Override
    public void run() {

        switch (_state){

            case 1:
                _outcome = processAllUsersJSON();
            case 2:
                _outcome = readGetCoordinates();
        }
    }

    public void setState(int state){
        this._state = state;
    }

    public String getOutcome(){
        return _outcome;
    }

}
