package com.donum.googleapiservice.Controller;

import com.donum.googleapiservice.Service.Geocoding.Geocoding;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/geocoding")
public class GeoController extends BaseController{


    boolean inProgress;
    int progress = 0;
    /**
     *
     *
     * @param address - first line of address
     * @param postcode
     *
     *
     * return @param latitude and longitude
     */
    @GetMapping(value = "get-coordinates/{address}/{postcode}")
    @ResponseBody
    public String convertToGeo(@PathVariable String address, @PathVariable String postcode){

        Geocoding convertAddress = new Geocoding(address,postcode);

        convertAddress.start();

        while(convertAddress.isAlive()){
            inProgress = true;
        }

        return  convertAddress.getCoordinates();
    }

    @Override
    public void loadController() {
        _controllerName = "GeoController";
    }
}
