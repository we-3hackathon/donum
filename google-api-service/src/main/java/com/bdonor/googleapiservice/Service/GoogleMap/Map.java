package com.bdonor.googleapiservice.Service.GoogleMap;

import com.bdonor.googleapiservice.Model.Map.StaticMap;
import com.bdonor.googleapiservice.Model.Variable.EnumAPIKey;
import com.bdonor.googleapiservice.Model.Variable.EnumGoogleMap;

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
     * EnumURL is in format:
     * center={center}&zoom={zoom}&size={size}&maptype={maptype}
     */
    public void buildMapOnlyURL(){

        _URL = EnumGoogleMap.STATIC_MAP + _googleMap.getCenter() + _googleMap.getZoom() + _googleMap.getSize() + _googleMap.getMapType() ;
    }


    public void buildMapPlotURL(String markers){

        _URL = _URL + markers + buildKeyURL();
    }

    private String buildKeyURL(){
        return "&key=" + EnumAPIKey.MAP_KEY.toString();
    }

    public String get_URL() {
        return _URL ;
    }

    }
