package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("api/user")
public class UserController {
	@Autowired
	private UserService userservice;
	
	
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAll(){
		try {
		List<User> list=userservice.findAll();
		return new ResponseEntity<List<User>>(list,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<User>>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
		}
	}
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<User> add(@RequestBody User user){
		try {
			userservice.save(user);
			return new ResponseEntity(HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
//	@RequestMapping(value = "/edit", method = RequestMethod.POST)
//	public ResponseEntity<User> add(){
//		return new ResponseEntity(HttpStatus.NO_CONTENT);
//	}
	
	
}
