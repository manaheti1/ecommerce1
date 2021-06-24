package com.example.demo.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;



@Service
public interface UserService extends JpaRepository<User, Integer>{
	
}
