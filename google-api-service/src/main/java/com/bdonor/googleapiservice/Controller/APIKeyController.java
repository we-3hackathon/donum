package com.bdonor.googleapiservice.Controller;

import com.bdonor.googleapiservice.Security.KeyHelper;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api-key")

public class APIKeyController extends BaseController {

    KeyHelper _keyContainer = new KeyHelper();

    @GetMapping(value = "/load/google-map")
    @ResponseBody
    public String googleMapKeys(){

        return _keyContainer.loadGoogleMapApiKey();
    }

    @GetMapping(value = "/load/google-places")
    @ResponseBody
    public String googlePlacesKeys(){

        return _keyContainer.loadGooglePlacesApiKey();
    }




    @Override
    public void loadController() {
        _controllerName = "APIKeyController";
    }
}
