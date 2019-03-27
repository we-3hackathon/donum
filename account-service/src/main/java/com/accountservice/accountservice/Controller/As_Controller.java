package com.accountservice.accountservice.Controller;

import com.accountservice.accountservice.Models.AccountService;
import com.accountservice.accountservice.Models.User;
import com.accountservice.accountservice.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Component
public class As_Controller {

    @Autowired
    private AccountService Service_functions;

    private UserRepository UserRepo;

    public As_Controller(UserRepository userRepo) {
        UserRepo = userRepo;
    }

    @ResponseBody
    @RequestMapping("/CreateTwo") // Works
    public String CreateTwo(@RequestParam String firstname) {
        User CreateUser = Service_functions.createNew(firstname);
        return firstname + " Added";
    }

    @ResponseBody
    @RequestMapping(value = "/create/{bloodGroup}/{firstname}/{_surname}/{_email}/{_addressline}/[_postcode}", method = RequestMethod.POST) // Works
    public String create( @PathVariable String bloodGroup , @PathVariable  String firstname, @PathVariable  String _surname, @PathVariable  String _email, @PathVariable  String _password, @PathVariable  String _addressline, @PathVariable  String _postcode){
        User CreateUser = Service_functions.create(bloodGroup, firstname,  _surname,  _email,  _password,  _addressline,  _postcode);
        System.out.println("this is working");
        return CreateUser.toString();
    }

    @ResponseBody
    @GetMapping("/getUser/{firstName}") // THIS KINDA WORKS BUT DOESNT RETURN ANYTHING
    public User getUser( @PathVariable String firstName ){
        System.out.println("Working");
        return Service_functions.getByfirstName(firstName);
    }

    @GetMapping("/getAll")
    public List<User> getAllUsers(){ // THIS DOESNT WORK
        System.out.println("This Works");
        return Service_functions.getAll();
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
