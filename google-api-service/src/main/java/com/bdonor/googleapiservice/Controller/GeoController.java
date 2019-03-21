package com.bdonor.googleapiservice.Controller;

import com.bdonor.googleapiservice.Service.Geocoding;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class GeoController {


    boolean inProgress;
    int progress = 0;
    /**
     *
     *
     * @param address - first line of address
     * @param postcode
     *
     * return @param latitude and longitude
     */
    @GetMapping(value = "/geocoding/{address}/{postcode}")
    @ResponseBody
    public String convertToGeo(@PathVariable String address, @PathVariable String postcode){

        Geocoding convertAddress = new Geocoding(address,postcode);

        convertAddress.start();

        while(convertAddress.isAlive()){
            inProgress = true;
        }

        return  convertAddress.getCoordinates();
    }
}
