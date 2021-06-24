package com.example.demo.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Orders;
import com.example.demo.Model.User;
import com.example.demo.Service.OrderService;
import com.example.demo.Service.UserService;
@RestController
@RequestMapping("api/order")
public class OrderController {
	@Autowired
	private OrderService orderservice;
	@Autowired
	private UserService usersservice;
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<Orders>> getAll(){
		try {
		List<Orders> list=orderservice.findAll();
		return new ResponseEntity<List<Orders>>(list,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Orders>>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
		}
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Orders> search(@PathVariable int id){
		
		Orders order=orderservice.findById(id).get();
//		List<Product> list2=new ArrayList<>();
//		for (int i=0;i<list.size();i++) {
//			if (list.get(i).getName().contains(name)) list2.add(list.get(i));
//		}
		return new ResponseEntity<Orders>(order,HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<Orders> add(@RequestBody Orders product){
		try {
			orderservice.save(product);
			return new ResponseEntity(HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Orders>> searchbyUser(@PathVariable int id){
		User user=usersservice.findById(id).get();
		List<Orders> order=orderservice.findAllByUser(user);
//		List<Product> list2=new ArrayList<>();
//		for (int i=0;i<list.size();i++) {
//			if (list.get(i).getName().contains(name)) list2.add(list.get(i));
//		}
		return new ResponseEntity<List<Orders>>(order,HttpStatus.OK);
		
	}
	
}
