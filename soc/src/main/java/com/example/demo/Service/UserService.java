package com.example.demo.Service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.demo.Model.User;



@Service
public interface UserService extends JpaRepository<User, Integer>{
	Optional<User> findByUsername(String username);
//	Optional<User> findByUserName(String username);
}
