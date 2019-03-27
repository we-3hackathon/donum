package com.bdonor.googleapiservice.Service;

import com.bdonor.googleapiservice.Model.Variables;

public class Plot {

    private StringBuilder _plotURL ;

    public String generatePlot(String JSONData){

        _plotURL = new StringBuilder();

        // read JSON from all users from account service
        // compare each with current lat,lat
        // apply formula
        // check radius condition
        // append to _plotURL

        return "";
    }


    public void addMarker(String _markerColor, String _label, String _cordX, String _cordY){

        String _markerURL = Variables.MARKER.toString() + _markerColor + "|" + "label:"+ _label +"|"+ _cordX + "," + _cordY;
        _plotURL.append(_markerURL);
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
