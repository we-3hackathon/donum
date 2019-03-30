package com.bdonor.accountservice.Repository;

import com.bdonor.accountservice.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public interface UserRepository extends MongoRepository<User, String> {

    public User findByFirstName(String firstName);

}
