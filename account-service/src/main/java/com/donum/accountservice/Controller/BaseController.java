package com.donum.accountservice.Controller;

import org.springframework.web.bind.annotation.GetMapping;

public abstract class BaseController {

    protected String _controllerName = "";

    @GetMapping("/test")
    public String testController(){
        loadController();
        return "connection: OK with " + _controllerName;
    }

    public abstract void loadController();
}
