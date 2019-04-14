package com.bdonor.uiservice.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public abstract class BaseController {

    protected String _controllerName = "";

    @GetMapping("/test")
    public String testController(){
        loadController();
        return "connection: OK with " + _controllerName;
    }

    public abstract void loadController();
}
