package com.bdonor.uiservice.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class BaseController {

    @GetMapping("/test")
    public String testController(){
        return "connection: OK";
    }
}
