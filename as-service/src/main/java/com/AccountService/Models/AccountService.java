package com.AccountService.Models;

import com.AccountService.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private UserRepository UserRepo;

    public User create(String _firstname, String _surname, String _email, int _nhsid, String _password, Address _address){
        return UserRepo.save( new User(_firstname, _surname, _email, _nhsid, _password, _address) );
    }

    public List<User> getAll(){
        return UserRepo.findAll();
    }

    public User getByFirstName(String _firstname){
        return UserRepo.findByFirstName(_firstname);
    }

    public User Update(String _firstname, String _surname, String _email, int _nhsid, String _password, Address _address) {
        User find = UserRepo.findByFirstName(_firstname);
        find.set_firstname(_firstname);
        find.set_surname(_surname);
        find.set_email(_email);
        find.set_nhsid(_nhsid);
        find.set_password(_password);
        find.set_address(_address);
        return UserRepo.save(find);
    }

    public void deleteAll(){
        UserRepo.deleteAll();
    }

    public void deleteByFirstName(String _firstname){
        User user = UserRepo.findByFirstName(_firstname);
        UserRepo.delete(user);
    }
}
