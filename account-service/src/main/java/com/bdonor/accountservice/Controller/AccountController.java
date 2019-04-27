package com.bdonor.accountservice.Controller;

//import com.bdonor.accountservice.Models.AccountHelper;
import com.bdonor.accountservice.Models.User;
//import com.bdonor.accountservice.Repository.UserRepository;
import com.bdonor.accountservice.Repository.CrudRepo;
import com.bdonor.accountservice.Repository.DynamoRepo;
//import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class AccountController {

    @Autowired
    private DynamoRepo dynamoRepo;

    @Autowired
    private CrudRepo crudRepo;

    @ResponseBody
    @PostMapping(value = "/create/{bloodGroup}/{firstname}/{surname}/{email}/{password}/{addressline}/{postcode}")
    public String Register( @PathVariable String bloodGroup , @PathVariable  String firstname, @PathVariable  String surname, @PathVariable  String email, @PathVariable  String password, @PathVariable  String addressline, @PathVariable  String postcode){
        dynamoRepo.createUserTest(bloodGroup, firstname,  surname,  email,  password,  addressline,  postcode);
        return "User added to Dynamo Database";
    }


    @ResponseBody
    @GetMapping(value = "/getUser/{email}")
    public String getUser(@PathVariable String email){
        System.out.println("getUser Running");
        User user = dynamoRepo.getSingleUser(crudRepo.findByEmail(email).get_id(), crudRepo.findByEmail(email).getEmail());
        return user.toString();
    }

    @GetMapping(value = "/deleteUser/{email}")
    public String deleteUser(@PathVariable String email) {
        User user = crudRepo.findByEmail(email);
        dynamoRepo.deleteUserDetails(user);
        return "User" + user.toString() + "Details deleted";
    }

//    public String Login(@PathVariable String email, String password) {
//
//        if(){
//
//        }
//
//        return "OK";
//    }
//



//    @ResponseBody
//    @GetMapping("/getUser/{firstname}") // Works Partially - Only works for one user within database, if there are more with the same name, error is given
//    public String getUser( @PathVariable String firstname ){
//        System.out.println("Working");
//        return Service_functions.getByfirstName(firstname).toString();
//    }
//
////    @ResponseBody
////    @GetMapping("/getAll")
////    public String getAllUsers(){ // Works
////        System.out.println("This Works");
////
////        String json = new Gson().toJson(Service_functions.getAll());
////
////        return json;
////    }
//
//    @ResponseBody
//    @RequestMapping("/updateUser/{bloodGroup}/{firstname}/{surname}/{email}/{password}/{addressline}/{password}") // Works Partially - Creates new user instead of updating current
//    public String updateUser( @PathVariable String bloodGroup , @PathVariable  String firstname, @PathVariable  String surname, @PathVariable  String email, @PathVariable  String password, @PathVariable  String addressline, @PathVariable  String postcode ){
//        User Update = Service_functions.Update(bloodGroup, firstname,  surname,  email,  password,  addressline,  postcode);
//        return Update.toString();
//    }
//
//    @ResponseBody
//    @GetMapping("/deleteUser/{firstname}") // Works
//    public String deleteUser(@PathVariable String firstname ){
//        Service_functions.deleteByfirstName(firstname);
//        return "Deleted" + firstname;
//    }
//
//    @ResponseBody
//    @GetMapping("/checkCredentials/{email}/{password}")
//    public String checkCredentials( @PathVariable String email, @PathVariable String password ){
//        if(Service_functions.checkCredentials(email, password)){
//            System.out.println("Login Success");
//            return "Success";
//        }
//        System.out.println("Login Failed");
//        return "Failed";
//    }
//
//    @ResponseBody
//    @RequestMapping("/deleteAll") // Works
//    public String deleteAll(){
//        Service_functions.deleteAll();
//        return "All Users Deleted!";
//    }
}
