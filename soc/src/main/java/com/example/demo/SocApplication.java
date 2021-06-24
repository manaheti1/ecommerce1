package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.demo.Service.UserService;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class SocApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocApplication.class, args);
	}

}
