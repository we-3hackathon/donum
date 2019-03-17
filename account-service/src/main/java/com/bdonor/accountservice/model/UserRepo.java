package com.bdonor.service.Database;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.repository.CrudRepository;

@SpringBootApplication
@ComponentScan({"com.bdonor"})
@EntityScan("com.bdonor")
@EnableJpaRepositories("com.example.repositories")
public interface UserRepo extends CrudRepository < applicationUsers, String> {
}
