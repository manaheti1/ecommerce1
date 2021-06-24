package com.example.demo.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.OrderItem;
import com.example.demo.Model.Orders;
import com.example.demo.Service.OrderItemService;
import com.example.demo.Service.OrderService;
@RestController
@RequestMapping("api/orderitem")
public class OrderItemController {
	@Autowired
	private OrderItemService orderitemservice;
	@Autowired
	private OrderService orderservice;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<OrderItem>> getAll(){
		try {
		List<OrderItem> list=orderitemservice.findAll();
		return new ResponseEntity<List<OrderItem>>(list,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<OrderItem>>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/search/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<OrderItem>> search(@PathVariable int id){
		List<OrderItem> y= orderitemservice.findAll();
		List<OrderItem> x=new ArrayList<>();
		for (int i=0;i<y.size();i++) {
			if (y.get(i).getOrder().getId()==id) 
			x.add(y.get(i));
		}

		return new ResponseEntity<List<OrderItem>>(x,HttpStatus.OK);
		
	}
	
//	@RequestMapping(va

	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<OrderItem>> searchbyOrderId(@PathVariable int id){
		Orders x=orderservice.findById(id).get();
		System.out.println(x.getId());
		List<OrderItem> y= orderitemservice.findAllByOrder(x);
		System.out.println(y.size());
		return new ResponseEntity<List<OrderItem>>(y,HttpStatus.OK);
		
	}
	
	
	
	
//	@RequestMapping(value = "/", method = RequestMethod.POST)
//	public ResponseEntity<OrderItem> add(@RequestBody OrderItem product){
//		try {
//			orderitemservice.save(product);
//			return new ResponseEntity(HttpStatus.OK);
//		}catch(Exception e){
//			return new ResponseEntity(HttpStatus.BAD_REQUEST);
//		}
//	}
	
	
	
}
