package com.bdonor.googleapiservice.Controller;


import com.bdonor.googleapiservice.Model.Variables;
import com.bdonor.googleapiservice.Service.Map;
import com.bdonor.googleapiservice.Service.Plot;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@RestController
public class MapController {

    Map googleMap ;

    /* Test to see controller is called */
    @RequestMapping("/check")
    @ResponseBody
    public String testConnection(){
        googleMap = new Map("London","13", Variables.MEDIUM_RES.toString(),Variables.ROADMAP.toString());
        //googleMap.buildURL();
        return "ok";
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

    @Deprecated
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

    @GetMapping(value = "/editmap/zoom/{zoom}")
    public void changeMapZoom(@PathVariable String zoom){

    }

    @GetMapping(value = "/editmap/resolution/{resolution}")
    public void changeResulution(@PathVariable String resolution){

    }

    @GetMapping(value = "/generatemap/{city}")
    public void generateAllMarkerMapURL(@PathVariable String city, HttpServletResponse http){

        try {
            // the default map
            googleMap = new Map("New York", "13", Variables.MEDIUM_RES.toString(), Variables.ROADMAP.toString());

            // generate the map
            Plot mapPlot = new Plot();

            // REST API to account-service GET @all-users
            String allUsers = getAllUsers();

            //System.out.println(allUsers);

            // for each in @all-users, addMarker
            //mapPlot.addMarker(mapPlot.setColour(blood), blood, lat, lng);
            System.out.println("method: " + mapPlot.processPlot(allUsers));

            // first part of the URL
            googleMap.buildMapOnlyURL();

            System.out.println("final " + mapPlot.getPlotURL());
            // plots URL
            googleMap.buildMapPlotURL(mapPlot.getPlotURL());

            System.out.println(googleMap.get_URL());
            // redirect to map
            //http.sendRedirect(googleMap.get_URL());

        }catch (Exception e){
            e.getMessage();
        }
    }

    @ResponseBody
    @RequestMapping("/account-service/all-users")
    public String getAllUsers(){

        String jsonData = "error";

        try {

            URL url = new URL("http://localhost:9090/getAll");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            String output = "E";
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                //System.out.println(output);
                jsonData = output;
            }

            conn.disconnect();
            return jsonData;

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

        return "Bad Request";
    }



}


