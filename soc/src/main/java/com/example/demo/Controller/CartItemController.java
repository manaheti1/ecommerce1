package com.example.demo.Controller;

import java.util.ArrayList;
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
import com.example.demo.Model.CartItem;
import com.example.demo.Model.OrderItem;
import com.example.demo.Model.Product;
import com.example.demo.Model.User;
import com.example.demo.Request.CartItemRequest;
import com.example.demo.Service.CartItemService;
import com.example.demo.Service.CartService;
import com.example.demo.Service.ProductService;
import com.example.demo.Service.UserService;
@RestController
@RequestMapping("api/cartitem")
public class CartItemController {
	@Autowired
	private CartItemService cartitemservice;
	@Autowired
	private ProductService productservice;
	@Autowired
	private CartService cartservice;
	@Autowired
	private UserService userservice;
	
//	@RequestMapping(value = "/getall", method = RequestMethod.GET)
//	public ResponseEntity<List<CartItem>> getAll(){
//		try {
//		List<CartItem> list=cartitemservice.findAll();
//		return new ResponseEntity<List<CartItem>>(list,HttpStatus.OK);
//		}catch(Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<List<CartItem>>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
//		}
//	}
//	@RequestMapping(value = "/add", method = RequestMethod.POST)
//	public ResponseEntity<CartItem> add(@RequestBody CartItem cartitem){
//		try {
//			
//			cartitemservice.save(cartitem);
//			return new ResponseEntity(HttpStatus.OK);
//		}catch(Exception e){
//			return new ResponseEntity(HttpStatus.BAD_REQUEST);
//		}
//	}
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public ResponseEntity<List<CartItemRequest>> getAllR(){
		try {
		List<CartItemRequest> list=new ArrayList<>();
		List<CartItem> b=cartitemservice.findAll();
		for (CartItem a:b) {
			list.add(new CartItemRequest(a.getId(),a.getProduct().getId(),1,a.getAmount()));
		}
		return new ResponseEntity<List<CartItemRequest>>(list,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<CartItemRequest>>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/cart/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<CartItem>> searchbyCustomerId(@PathVariable int id){
		
		User c=userservice.getById(id);
		Cart cart=cartservice.findByUserId(c.getId()).get();
		System.out.println(cart.getId());
		List<CartItem> y= cartitemservice.findAllByCart(cart);
		System.out.println(y.size());
		return new ResponseEntity<List<CartItem>>(y,HttpStatus.OK);
		
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Cart> del(@PathVariable int id){
		
		cartitemservice.deleteById(id);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<CartItem> addr(@RequestBody CartItemRequest cartitem){
			Product a=productservice.findById(cartitem.getProductid()).get();
			
			Cart b=cartservice.findByUserId(cartitem.getUserid()).get();
			int amount=cartitem.getAmount();
			CartItem d=new CartItem(0,amount,a,b);
			
			cartitemservice.save(d);
			
			return new ResponseEntity<CartItem>(d,HttpStatus.OK);
		
	}
	
}
