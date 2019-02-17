package com.bdonor.SpringMain.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class UserController {

    @RequestMapping(value = "/")
    public String register(){
        return "Register.html";
    }
}
