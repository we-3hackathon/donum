package com.bdonor.googleapiservice.Service;

import com.bdonor.googleapiservice.Model.StaticMap;
import com.bdonor.googleapiservice.Model.Variables;

public class Map {


    StaticMap _googleMap;
    String _URL = "";

    /*
    * @param city
    * @param zoom
    * @param size
    * @param maptype
    * */

    public Map(String city, String zoom, String size, String maptype) {
        _googleMap = new StaticMap();

        _googleMap.setCenter(city);
        _googleMap.setZoom(zoom);
        _googleMap.setSize(size);
        _googleMap.setMapType(maptype);
    }


    /**
     * URL_ENV is in format:
     * center={center}&zoom={zoom}&size={size}&maptype={maptype}
     */
    public void buildURL(){

        _URL = Variables.STATIC_MAP + _googleMap.getCenter() + _googleMap.getZoom() + _googleMap.getSize() + _googleMap.getMapType() + "&key=" + Variables.API_KEY.toString();
    }

    /*
    public void addMarker(){

        _markerURL = _markerURL + _markerColor + "|" + "label:"+ _label +"|"+ _cordX + "," + _cordY;
    }*/

    public String get_URL() {
        return _URL ;
    }

    /*
    public void checkSize(int donorInMap){

        /* MORE THAN ONE DONOR IN MAP
        if(donorInMap != 1){

            for(int i=0; i<donorInMap; i++){

                _URL =+ _URL + _
            }

        }*/

    }
