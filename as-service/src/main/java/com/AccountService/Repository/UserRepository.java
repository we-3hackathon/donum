package com.AccountService.Repository;

import com.AccountService.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    public User findByFirstName( String _firstname);
}
