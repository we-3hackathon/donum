package com.bdonor.springmain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class UserController {

    @RequestMapping(value = "/")
    public String register(){
        return "Register.html";
    }
}
