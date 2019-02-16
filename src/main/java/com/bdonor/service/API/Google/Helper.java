package com.bdonor.service.API.Google;

public class Helper {

    private final String _APIKEY = "&AIzaSyBz-en4IzG0aeAxcGWc3Xo0fURt-Fb2-sU";

    private String _URL = "https://maps.googleapis.com/maps/api/staticmap?center=";

    private String _city = "";

    private String _zoom = "";

    private String _size = "";

    private String _markerColor = "";

    private String _markerURL= "&markers=color:";

    private String _label = "";

    private String _cordX = "";

    private String _cordY = "";


    public void buildURL(){

        _URL = _URL + _city + ",&" + "zoom=" + "&" + "size=" + _size + "&maptype=roadmap";

    }

    public void addMarker(){

        _markerURL = _markerURL + _markerColor + "|" + "label:"+ _label +"|"+ _cordX + "," + _cordY;
    }


    public String get_APIKEY() {
        return _APIKEY;
    }

    public String get_URL() {
        return _URL;
    }

    public String get_city() {
        return _city;
    }

    public void set_city(String _city) {
        this._city = _city;
    }

    public String get_zoom() {
        return _zoom;
    }

    public void set_zoom(String _zoom) {
        this._zoom = _zoom;
    }

    public void checkSize(int donorInMap){

        /* MORE THAN ONE DONOR IN MAP
        if(donorInMap != 1){

            for(int i=0; i<donorInMap; i++){

                _URL =+ _URL + _
            }

        }*/

        _URL = _URL + _markerURL + _APIKEY;

    }
}
