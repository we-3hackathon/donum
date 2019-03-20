package com.bdonor.googleapiservice.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GeoController {


    /**
     *
     *
     * @param address - first line of address
     * @param postcode
     *
     * return @param latitude and longitude
     */
    @RequestMapping("/geocoding")
    public String convertToGeo(String address, String postcode){

        return "Your Lat is Lon is";
    }
}
