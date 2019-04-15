package com.bdonor.accountservice.Controller;

import com.bdonor.accountservice.Models.AccountHelper;
import com.bdonor.accountservice.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@Component
public class AccountController {

    @Autowired
    private AccountHelper Service_functions;

    @ResponseBody // Works
    @GetMapping(value = "/create/{bloodGroup}/{firstname}/{_surname}/{_email}/{_password}/{_addressline}/{_password}")
    public String create( @PathVariable String bloodGroup , @PathVariable  String firstname, @PathVariable  String _surname, @PathVariable  String _email, @PathVariable  String _password, @PathVariable  String _addressline, @PathVariable  String _postcode){
        User CreateUser = Service_functions.create(bloodGroup, firstname,  _surname,  _email,  _password,  _addressline,  _postcode);
        System.out.println("this is working");
        return CreateUser.toString();
    }

    @ResponseBody
    @GetMapping("/getUser/{firstname}") // Works Partially - Only works for one user within database, if there are more with the same name, error is given
    public String getUser( @PathVariable String firstname ){
        System.out.println("Working");
        return Service_functions.getByfirstName(firstname).toString();
    }

    @ResponseBody
    @GetMapping("/getAll")
    public String getAllUsers(){ // Works
        System.out.println("This Works");
        return Service_functions.getAll().toString();
    }

    @ResponseBody
    @RequestMapping("/updateUser/{bloodGroup}/{firstname}/{_surname}/{_email}/{_password}/{_addressline}/{_password}") // Works Partially - Creates new user instead of updating current
    public String updateUser( @PathVariable String bloodGroup , @PathVariable  String firstname, @PathVariable  String _surname, @PathVariable  String _email, @PathVariable  String _password, @PathVariable  String _addressline, @PathVariable  String _postcode ){
        User Update = Service_functions.Update(bloodGroup, firstname,  _surname,  _email,  _password,  _addressline,  _postcode);
        return Update.toString();
    }

    @ResponseBody
    @GetMapping("/deleteUser/{firstname}") // Works
    public String deleteUser(@PathVariable String firstname ){
        Service_functions.deleteByfirstName(firstname);
        return "Deleted" + firstname;
    }

    @ResponseBody
    @GetMapping("/checkCredentials/{email}/{_password}") // Error - No property email found for type User! Did you mean '_email'?
    public boolean checkCredentials( @PathVariable String email, @PathVariable String _password ){
        if(Service_functions.checkCredentials(email ,_password)){
            System.out.println("Login Success");
            return true;
        }
        System.out.println("Login Failed");
        return false;
    }

    @ResponseBody
    @RequestMapping("/deleteAll") // Works
    public String deleteAll(){
        Service_functions.deleteAll();
        return "All Users Deleted!";
    }
}
