package com.bdonor.uiservice.Controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController()
@RequestMapping("/login")
public class LoginController extends BaseController{


    @RequestMapping(value="/verifyCredentials", method= RequestMethod.POST)
    public void mainLogin(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletResponse http) throws IOException {

        System.out.println(username);
        System.out.println(password);
        http.sendRedirect("/map/view/static");
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
