package com.bdonor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class Application {

	public static void main(String[] args) {
		System.out.println("hello world");
		SpringApplication.run(Application.class, args);
	}

	@RequestMapping("Home")
	@ResponseBody
	public String getString(){
		return "Home does this work";
	}

}

