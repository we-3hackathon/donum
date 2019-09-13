package com.donum.googleapiservice.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

public abstract class BaseController {

    protected String _controllerName = "";

    @GetMapping("/test")
    public ResponseEntity<String> testController(){
        loadController();
        return new ResponseEntity<>("connection: OK with " + _controllerName, HttpStatus.OK);
    }

    public abstract void loadController();
}