package com.bdonor.accountservice.Controller;

import com.bdonor.accountservice.Models.AccountHelper;
import com.bdonor.accountservice.Models.User;
import com.bdonor.accountservice.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Component
public class AccountController {

    @Autowired
    private AccountHelper Service_functions;

    private UserRepository UserRepo;

    public AccountController(UserRepository userRepo) {
        UserRepo = userRepo;
    }

    @ResponseBody
    @RequestMapping("/CreateTwo") // Works
    public String CreateTwo(@RequestParam String firstname) {
        User CreateUser = Service_functions.createNew(firstname);
        return firstname + " Added";
    }

    @ResponseBody
    @RequestMapping("/testController") // Works
    public String testResponse() {

        System.out.println("a");
        System.out.println("a");

        return "Response: OK";
    }
    @ResponseBody
    @GetMapping(value = "/create/{bloodGroup}/{firstname}/{_surname}/{_email}/{_addressline}/{_postcode}/{_password}") // Works
    public String create( @PathVariable String bloodGroup , @PathVariable  String firstname, @PathVariable  String _surname, @PathVariable  String _email, @PathVariable  String _password, @PathVariable  String _addressline, @PathVariable  String _postcode){
        User CreateUser = Service_functions.create(bloodGroup, firstname,  _surname,  _email,  _password,  _addressline,  _postcode);
        System.out.println("this is working");
        return CreateUser.toString();
    }

    @ResponseBody
    @GetMapping("/getUser/{firstName}") // THIS KINDA WORKS BUT DOESNT RETURN ANYTHING
    public String getUser( @PathVariable String firstName ){
        System.out.println("Working");
        return Service_functions.getByfirstName(firstName).toString();
    }

    @ResponseBody
    @GetMapping("/getAll")
    public String getAllUsers(){ // THIS DOESNT WORK
        System.out.println("This Works");
        return Service_functions.getAll().toString();
    }

    @ResponseBody
    @RequestMapping("/updateUser/{bloodGroup}/{firstname}/{_surname}/{_email}/{_addressline}/[_postcode")
    public String updateUser( @PathVariable String bloodGroup , @PathVariable  String firstname, @PathVariable  String _surname, @PathVariable  String _email, @PathVariable  String _password, @PathVariable  String _addressline, @PathVariable  String _postcode ){
        User Update = Service_functions.Update(bloodGroup, firstname,  _surname,  _email,  _password,  _addressline,  _postcode);
        return Update.toString();
    }

    @ResponseBody
    @GetMapping("/deleteUser/{firstName}")
    public String delete(@PathVariable("firstName") String firstName ){
        Service_functions.deleteByfirstName(firstName);
        return "Deleted" + firstName;
    }

    @ResponseBody
    @RequestMapping("/Test")
    public String Test(@RequestParam String firstName){
        System.out.println("Working");
        User aa = Service_functions.getByfirstName(firstName);
        return "found" + aa.toString();
    }

    @RequestMapping("/deleteAll") // Works
    @ResponseBody
    public String deleteAll(){
        Service_functions.deleteAll();
        return "All Users Deleted!";
    }

}
