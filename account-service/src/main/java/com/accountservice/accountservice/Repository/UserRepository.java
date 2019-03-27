package com.accountservice.accountservice.Repository;

import com.accountservice.accountservice.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository
public interface UserRepository extends MongoRepository<User, String> {

    public User findByFirstName(String firstName);

}
