package com.bdonor.SpringMain.Controller;

import com.bdonor.service.Database.UserRepo;
import com.bdonor.service.Database.applicationUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class UserController {

    // Obj of UserRepo
    @Autowired
    UserRepo repo;

    @RequestMapping(value = "/")
    public String register(){
        return "Register.html";
    }

    // HTML calls this functions so the user data gets added to the database
    @RequestMapping("/addUsersDatabase")
    public String addUsersDatabase(applicationUsers users) {

        repo.save(users);
        return "Register.html";
    }
}
