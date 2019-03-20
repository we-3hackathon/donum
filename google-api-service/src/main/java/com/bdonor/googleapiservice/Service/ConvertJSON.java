package com.bdonor.googleapiservice.Service;

import org.json.*;

public class ConvertJSON {


    private String _JSONDATA = "";

    public ConvertJSON(String data){
        this._JSONDATA = data;
    }

    public void readFile(){

        try {
            JSONObject obj = new JSONObject(_JSONDATA);

            JSONArray usersArray = obj.getJSONArray("users");

            for (int i = 0; i < usersArray.length(); i++) {
                String post_id = usersArray.getJSONObject(i).getString("post_id");
            }

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }


    }

}
