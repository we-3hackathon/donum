package com.bdonor.springmain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MainController {
    @RequestMapping(value = "/index")
    public String Index(){ return "index.html"; }
}




