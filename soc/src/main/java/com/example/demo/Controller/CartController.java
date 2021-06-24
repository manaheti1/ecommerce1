package com.example.demo.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Cart;
import com.example.demo.Model.CartItem;
import com.example.demo.Model.OrderItem;
import com.example.demo.Model.Orders;
import com.example.demo.Model.User;
import com.example.demo.Service.CartItemService;
import com.example.demo.Service.CartService;
import com.example.demo.Service.OrderItemService;
import com.example.demo.Service.OrderService;
import com.example.demo.Service.UserService;
import com.example.demo.Util.Convert;
@RestController
@RequestMapping("api/cart")
public class CartController {
	@Autowired
	private CartService cartservice;
	@Autowired
	private UserService userservice;
	@Autowired
	private OrderService orderservice;
	@Autowired
	private CartItemService cartitemservice;
	@Autowired
	private OrderItemService orderitemservice;
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/checkout/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> checkout(@PathVariable int id){
		try {
			User x=userservice.findById(id).get();
			Cart y=new Cart();
//			List<Cart> list=cartservice.findAll();
//			for (int i=0;i<list.size();i++) {
//				if (list.get(i).getUser().getId()==id) {
//					y=list.get(i);
//				}
//			}
			y=cartservice.findByUserId(id).get();
			
			List<CartItem> cartItem=cartitemservice.findAllByCart(y);
//			for (int i=0;i<all.size();i++) {
//				System.out.println(all.get(i).getCart().getId()+" ");
//				if (all.get(i).getCart().getId()==y.getId()) {
//					
//					cartItem.add(all.get(i));
//				}
//			}
			Orders z=new Orders(0,x);
			List<OrderItem> k= Convert.convertCartItem(cartItem);
			for (OrderItem i:k) {
				i.setOrder(z);
			}
			orderservice.save(z);
			orderitemservice.saveAll(k);
//			cartitemservice.del
			for (int i=0;i<cartItem.size();i++) {
				
				cartitemservice.delete(cartItem.get(i));
				
			}
			cartitemservice.deleteByCartId(y.getId());
		return new ResponseEntity<Orders>(z,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Cart>>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public ResponseEntity<List<Cart>> getAll(){
		try {
		List<Cart> list=cartservice.findAll();
		return new ResponseEntity<List<Cart>>(list,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Cart>>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
		}
	}
	
}
