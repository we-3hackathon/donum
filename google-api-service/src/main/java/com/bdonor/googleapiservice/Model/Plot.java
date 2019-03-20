package com.bdonor.googleapiservice.Model;

public class Plot extends User{

    private String _color;

    public Plot(String lat, String lon, String id, String bloodGroup) {
        super(lat, lon, id, bloodGroup);
        _color = setColour(bloodGroup);
    }

    public String setColour(String bloodGroup){

        /* Other bloodGroups missing */
        switch (bloodGroup){

            case "A":
                return "red";

            case "B":
                return "red";

            case "O":
                return "red";

            default:
                return "grey";

        }
    }
}
