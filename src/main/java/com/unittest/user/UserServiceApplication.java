package com.unittest.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		System.out.println("hello world. I am just borned");
		SpringApplication.run(UserServiceApplication.class, args);
	}
}
