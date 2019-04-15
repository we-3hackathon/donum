package com.bdonor.accountservice.Models;

import com.bdonor.accountservice.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AccountHelper {

    @Autowired
    private UserRepository UserRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User create(String bloodGroup, String firstname, String _surname, String _email, String _password, String _addressline, String _postcode) { // This Works
        User user = UserRepo.findByEmail(_email);
        if(user != null) {
            System.out.println("User Exists");
            return new User();
        }
        return UserRepo.save(new User(bloodGroup, firstname, _surname, _email, bCryptPasswordEncoder.encode(_password), _addressline, _postcode));
    }

    public User getByfirstName(String firstName) {
        return UserRepo.findByFirstName(firstName);
    }

    public List<User> getAll() {
        return UserRepo.findAll();
    }

    public User Update(String bloodGroup, String firstname, String _surname, String _email, String _password, String _addressline, String _postcode) {

        User SpecificUser = UserRepo.findByFirstName(firstname);
        SpecificUser.setBloodGroup(bloodGroup);
        SpecificUser.setfirstName(firstname);
        SpecificUser.set_surname(_surname);
        SpecificUser.setEmail(_email);
        SpecificUser.set_password(bCryptPasswordEncoder.encode(_password));
        SpecificUser.set_addressline(_addressline);
        SpecificUser.set_postcode(_postcode);

        return UserRepo.save(SpecificUser);
    }

    public void deleteAll() {
        UserRepo.deleteAll();
    }

    public void deleteByfirstName(String firstname) {
        User user = UserRepo.findByFirstName(firstname);
        System.out.println(user);
        UserRepo.delete(user);
    }

    public boolean checkCredentials(String _email, String _password) {
        try {
            User user = UserRepo.findByEmail(_email);
            if (user != null) {
                if (bCryptPasswordEncoder.matches(_password, user.get_password())) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}