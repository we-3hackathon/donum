package com.bdonor.googleapiservice.Controller;


import com.bdonor.googleapiservice.Model.Variables;
import com.bdonor.googleapiservice.Service.Geocoding;
import com.bdonor.googleapiservice.Service.Map;
import com.bdonor.googleapiservice.Service.Plot;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class MapController {

    Map googleMap ;

    /* Test to see controller is called */
    @RequestMapping("/check")
    @ResponseBody
    public String testConnection(){
        googleMap = new Map("London","13", Variables.MEDIUM_RES.toString(),Variables.ROADMAP.toString());
        //googleMap.buildURL();
        return googleMap.get_URL();
    }


    /* Test to check Helper class is OK */
    @RequestMapping(value = "/url", method = RequestMethod.GET)
    public void redirect (HttpServletResponse http){
        try{
            googleMap = new Map("London","13", Variables.MEDIUM_RES.toString(),Variables.ROADMAP.toString());
            //googleMap.buildURL();
        http.sendRedirect(googleMap.get_URL());
        }catch (IOException ex){
        }
    }

    @RequestMapping(value = "/account-service/getAll", method = RequestMethod.GET)
    public void requestUsersData(){

    }

    @GetMapping(value = "/generatemap/{lat}/{lng}/{city}/{blood}")
    public void generateMapURL(@PathVariable String lat, @PathVariable String lng,@PathVariable String city, @PathVariable String blood,HttpServletResponse http){

        try {
            // the default map
            googleMap = new Map(city, "13", Variables.MEDIUM_RES.toString(), Variables.ROADMAP.toString());

            // generate the map
            Plot mapPlot = new Plot();

            // set the first marker
            mapPlot.addMarker(mapPlot.setColour(blood), blood, lat, lng);

            // first part of the URL
            googleMap.buildMapOnlyURL();

            // plots URL
            googleMap.buildMapPlotURL(mapPlot.getPlotURL());

            // redirect to map
            http.sendRedirect(googleMap.get_URL());

        }catch (Exception e){
            e.getMessage();
        }
    }

    @GetMapping(value = "/editmap/{zoom}")
    public void changeMapZoom(@PathVariable String zoom){


    }

    @GetMapping(value = "/generatemap/{city}")
    public void generateAllMarkerMapURL(@PathVariable String city, HttpServletResponse http){

        try {
            // the default map
            googleMap = new Map(city, "13", Variables.MEDIUM_RES.toString(), Variables.ROADMAP.toString());

            // generate the map
            Plot mapPlot = new Plot();

            // REST API to account-service GET @all-users

            // for each in @all-users, addMarker
            //mapPlot.addMarker(mapPlot.setColour(blood), blood, lat, lng);

            // first part of the URL
            googleMap.buildMapOnlyURL();

            // plots URL
            googleMap.buildMapPlotURL(mapPlot.getPlotURL());

            // redirect to map
            http.sendRedirect(googleMap.get_URL());

        }catch (Exception e){
            e.getMessage();
        }
    }

}
