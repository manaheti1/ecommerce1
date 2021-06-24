package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Cart;
import com.example.demo.Model.User;
import com.example.demo.Service.CartService;
import com.example.demo.Service.UserService;
@RestController
@RequestMapping("api/user")
public class UserController {
	@Autowired
	private UserService userservice;
	@Autowired
	private CartService cartservice;
	
	
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAll(){
//		try {
		List<User> list=userservice.findAll();
		return new ResponseEntity<List<User>>(list,HttpStatus.OK);
//		}catch(Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<List<User>>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
//		}
	}
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<User> add(@RequestBody User user){
//		try {
		user.setRole("customer");
			userservice.save(user);
			Cart a=new Cart();
			a.setUser(user);
			cartservice.save(a);
			return new ResponseEntity<User>(user,HttpStatus.OK);
//		}catch(Exception e){
//			return new ResponseEntity(HttpStatus.BAD_REQUEST);
//		}
	}
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<User> login(@RequestBody User user){
//		try {
			User a=userservice.findByUsername(user.getUsername()).get();
			if (a.getPassword().equals(user.getPassword())) {
				return new ResponseEntity<User>(a,HttpStatus.OK);
			}
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
//		}catch(Exception e){
//			return new ResponseEntity(HttpStatus.BAD_REQUEST);
//		}
	}
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> edit(@RequestBody User user,@PathVariable String id){
//		try {
			userservice.save(user);
			return new ResponseEntity(HttpStatus.OK);
//		}catch(Exception e){
//			return new ResponseEntity(HttpStatus.BAD_REQUEST);
//		}
	}
	
//	@RequestMapping(value = "/edit", method = RequestMethod.POST)
//	public ResponseEntity<User> add(){
//		return new ResponseEntity(HttpStatus.NO_CONTENT);
//	}
	
	
}
