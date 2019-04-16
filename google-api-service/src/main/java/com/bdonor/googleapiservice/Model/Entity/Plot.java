package com.bdonor.googleapiservice.Model.Entity;

public class Plot extends User {

    private String _color;
    private String _lat;
    private String _lon;


    public Plot(String _postcode, String _address, String _bloodGroup) {
        super(_postcode, _address, _bloodGroup);
        _color = setColour(_bloodGroup);
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

    public String get_color() {
        return _color;
    }

    public void set_color(String _color) {
        this._color = _color;
    }

    public String get_lat() {
        return _lat;
    }

    public void set_lat(String _lat) {
        this._lat = _lat;
    }

    public String get_lon() {
        return _lon;
    }

    public void set_lon(String _lon) {
        this._lon = _lon;
    }
}
