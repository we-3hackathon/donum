package com.bdonor.googleapiservice.Controller;


import com.bdonor.googleapiservice.Helper.Helper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class MapController {

    Helper googleAPI = new Helper();

    /* Test to see controller is called */
    @RequestMapping("/check")
    public String testConnection(){
        return "redirect:"+ googleAPI._exampleURL;
    }


    /* Test to check Helper class is OK */
    @RequestMapping(value = "/url", method = RequestMethod.GET)
    public void redirect (HttpServletResponse http){
        try{
        http.sendRedirect(googleAPI._exampleURL);
        }catch (IOException ex){
        }
    }

    @RequestMapping(value = "/account-service/getAll", method = RequestMethod.GET)
    public void requestUsersData(){



    }
}
