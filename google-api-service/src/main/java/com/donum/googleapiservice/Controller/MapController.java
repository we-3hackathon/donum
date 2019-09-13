package com.donum.googleapiservice.Controller;

import com.donum.googleapiservice.InternalService.AccountServiceHelper;
import com.donum.googleapiservice.Enum.EnumGoogleMap;
import com.donum.googleapiservice.Service.GoogleMap.Map;
import com.donum.googleapiservice.Service.GoogleMap.SingletonPlot;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/map")
public class MapController extends BaseController{

    private Map googleMap ;
    private SingletonPlot mapPlot = SingletonPlot.getInstance();

    final static Logger logger = Logger.getLogger(MapController.class);


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

            // redirect to map
            //http.sendRedirect(googleMap.get_URL());

            return googleMap.getURL();
        }catch (Exception e){
            logger.error(e.getMessage());
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


