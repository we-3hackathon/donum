package service.account;

import com.bdonor.springmain.model.User;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.repository.CrudRepository;

@SpringBootApplication
@ComponentScan({"com.bdonor"})
@EntityScan("com.bdonor")
public interface Account extends CrudRepository <User, String> {

}