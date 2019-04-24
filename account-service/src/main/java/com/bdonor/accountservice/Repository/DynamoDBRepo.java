package com.bdonor.accountservice.Repository;

import com.bdonor.accountservice.Models.User;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface DynamoDBRepo extends CrudRepository<User, String> {

    User findByFirstName(String firstName);

    User findByEmail(String email);

}

