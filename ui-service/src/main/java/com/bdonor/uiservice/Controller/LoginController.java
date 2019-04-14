package com.bdonor.uiservice.Controller;

import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/login")
public class LoginController extends BaseController{

    @ResponseBody()
    @RequestMapping(value="/verifyCredentials", method= RequestMethod.POST)
    public String mainLogin(@RequestParam("username") String username, @RequestParam("password") String password) {

        return "You reached";
    }

    public boolean verifyCredentials(){

        // rest call to account-service/verify/{loginID}/{password}
        // response: OK/Error
        // direct user to page
        return true;
    }

    @Override
    public void loadController() {
        _controllerName = "LoginController";
    }
}
