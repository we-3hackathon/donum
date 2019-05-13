package com.bdonor.googleapiservice.Controller;

import com.bdonor.googleapiservice.InternalService.AccountServiceHelper;
import com.bdonor.googleapiservice.Model.Variable.EnumGoogleMap;
import com.bdonor.googleapiservice.Service.GoogleMap.Map;
import com.bdonor.googleapiservice.Service.GoogleMap.SingletonPlot;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@RestController
@RequestMapping("/map")
public class MapController extends BaseController{

    private Map googleMap ;
    private SingletonPlot mapPlot = SingletonPlot.getInstance();

    @GetMapping(value = "/editmap/zoom/{zoom}")
    public void changeMapZoom(@PathVariable String zoom){

    }

    @GetMapping(value = "/editmap/resolution/{resolution}")
    public void changeResulution(@PathVariable String resolution){

    }

    @GetMapping(value = "/generatemap/{city}")
    public String generateAllMarkerMapURL(@PathVariable String city, HttpServletResponse http){

        try {
            // the default map
            googleMap = new Map(city, "13", EnumGoogleMap.MEDIUM_RES.toString(), EnumGoogleMap.ROADMAP.toString());

            // REST API to account-service GET @all-users
            String allUsers = getAllUsers();


            // for each in @all-users, addMarker
            mapPlot.processPlot(allUsers);

            // first part of the URL
            googleMap.buildMapOnlyURL();

            // plots URL
            googleMap.buildMapPlotURL(mapPlot.getPlotURL());

            System.out.println(googleMap.getURL());

            // redirect to map
            //http.sendRedirect(googleMap.get_URL());

            return googleMap.getURL();
        }catch (Exception e){
            e.getMessage();
        }
            return "Issue with generateAllMarkerURL()";
    }

    @ResponseBody
    @RequestMapping("/account-service/all-users")
    /* Gets all the users in DB from account-service */
    public String getAllUsers(){

        return AccountServiceHelper.requestAllUsers();
    }

    @Override
    public void loadController() {
            _controllerName = "MapController";
    }

}


