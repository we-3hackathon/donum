package com.bdonor.service.Database;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @RequestMapping("/")
    public String register(){
        return "Register.jsp";
    }
}
