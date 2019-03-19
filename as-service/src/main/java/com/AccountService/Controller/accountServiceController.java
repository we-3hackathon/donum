package com.AccountService.Controller;

import com.AccountService.Models.AccountService;
import com.AccountService.Models.Address;
import com.AccountService.Models.User;
import com.AccountService.Repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class accountServiceController {

    private AccountService Functions;

    @RequestMapping("/Create")
    public String create(@RequestParam  String _firstname, @RequestParam String _surname, @RequestParam String _email, @RequestParam int _nhsid, @RequestParam String _password, @RequestParam Address _address){
        User CreateUser = Functions.create(_firstname, _surname, _email, _nhsid, _password, _address);
        return CreateUser.toString();
    }

    @RequestMapping("/getUser")
    public User getUser( String _firstname ){
        return Functions.getByFirstName(_firstname);
    }

    @RequestMapping("/getAll")
    public List<User> getAllUsers(){
        return Functions.getAll();
    }

    @RequestMapping("/updateUser")
    public String updateUser( @RequestParam  String _firstname, @RequestParam String _surname, @RequestParam String _email, @RequestParam int _nhsid, @RequestParam String _password, @RequestParam Address _address ){
        User Update = Functions.Update(_firstname, _surname, _email, _nhsid, _password, _address);
        return Update.toString();
    }

    @RequestMapping("/deleteUser")
    public String delete( @RequestParam String _firstname){
        Functions.deleteByFirstName(_firstname);
        return "Deleted" + _firstname;
    }

    @RequestMapping("/deleteAll")
    public String deleteAll(){
        Functions.deleteAll();
        return "All Users Deleted!";
    }
}
