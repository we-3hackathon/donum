package com.bdonor.accountservice.Models;

import com.bdonor.accountservice.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountHelper {

    @Autowired
    private UserRepository UserRepo;

    public User create( String bloodGroup, String firstname, String _surname, String _email, String _password, String _addressline, String _postcode ){ // This Works
        return UserRepo.save( new User(bloodGroup, firstname,  _surname,  _email,  _password,  _addressline,  _postcode) );
    }

    public User getByfirstName(String firstName){
        return UserRepo.findByFirstName(firstName);
    }

    public List<User> getAll(){
        return UserRepo.findAll();
    }

    public User Update(String bloodGroup, String firstname, String _surname, String _email, String _password, String _addressline, String _postcode) {
        User SpecificUser = UserRepo.findByFirstName(firstname);

        SpecificUser.setBloodGroup(bloodGroup);
        SpecificUser.setfirstName(firstname);
        SpecificUser.set_surname(_surname);
        SpecificUser.set_email(_email);
        SpecificUser.set_password(_password);
        SpecificUser.set_addressline(_addressline);
        SpecificUser.set_postcode(_postcode);

        return UserRepo.save(SpecificUser);
    }

    public void deleteAll(){
        UserRepo.deleteAll();
    }

    public void deleteByfirstName(String firstname){
        User user = UserRepo.findByFirstName(firstname);
        System.out.println(user);
        UserRepo.delete(user);
    }

}
