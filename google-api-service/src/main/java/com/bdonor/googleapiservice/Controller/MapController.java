package com.bdonor.googleapiservice.Controller;


import com.bdonor.googleapiservice.Model.Variables;
import com.bdonor.googleapiservice.Service.Map;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
        googleMap.buildURL();
        return googleMap.get_URL();
    }


    /* Test to check Helper class is OK */
    @RequestMapping(value = "/url", method = RequestMethod.GET)
    public void redirect (HttpServletResponse http){
        try{
            googleMap = new Map("London","13", Variables.MEDIUM_RES.toString(),Variables.ROADMAP.toString());
            googleMap.buildURL();
        http.sendRedirect(googleMap.get_URL());
        }catch (IOException ex){
        }
    }

    @RequestMapping(value = "/account-service/getAll", method = RequestMethod.GET)
    public void requestUsersData(){

    }
}
