package com.bdonor.accountservice.controller;

import com.bdonor.service.Database.UserRepo;
import com.bdonor.service.Database.applicationUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class UserController {
<<<<<<< HEAD:src/main/java/com/bdonor/SpringMain/Controller/UserController.java

    // Obj of UserRepo
    @Autowired
    UserRepo repo;

=======
>>>>>>> 56a41a740568eeee2c56c8444e3b07e82dde702f:account-service/src/main/java/com/bdonor/accountservice/controller/UserController.java
    @RequestMapping(value = "/")
    @ResponseBody
    public String register(){
        return "Register.html";
    }

<<<<<<< HEAD:src/main/java/com/bdonor/SpringMain/Controller/UserController.java
    // HTML calls this functions so the user data gets added to the database
    @RequestMapping("/addUsersDatabase")
    public String addUsersDatabase(applicationUsers users) {

        repo.save(users);
        return "Register.html";
    }
=======
>>>>>>> 56a41a740568eeee2c56c8444e3b07e82dde702f:account-service/src/main/java/com/bdonor/accountservice/controller/UserController.java
}
