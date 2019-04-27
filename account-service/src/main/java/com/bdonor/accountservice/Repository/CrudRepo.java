package com.bdonor.accountservice.Repository;

import com.bdonor.accountservice.Models.User;
//import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrudRepo extends CrudRepository<User, String> {

    List<User> findByFirstName(String firstName); // Might not be needed, getAll will cost alot

    // Only unique val
    User findByEmail(String email);

}

