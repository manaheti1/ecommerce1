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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.CartItem;
import com.example.demo.Model.OrderItem;
import com.example.demo.Model.Product;
import com.example.demo.Service.CartItemService;
import com.example.demo.Service.OrderItemService;
import com.example.demo.Service.ProductService;
@RestController
@RequestMapping("api/product")
public class ProductController {
	@Autowired
	private ProductService productservice;
	@Autowired
	private CartItemService cartitemservice;
	@Autowired
	private OrderItemService orderitemservice;
	
	@RequestMapping(params = "name", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> search(@RequestParam("name") String name){
		name=name.toUpperCase();
		List<Product> list=productservice.findAll();
		List<Product> list2=new ArrayList<>();
		for (int i=0;i<list.size();i++) {
			if (list.get(i).getName().toUpperCase().contains(name)) list2.add(list.get(i));
		}
		return new ResponseEntity<List<Product>>(list2,HttpStatus.OK);
		
	}
	
	@RequestMapping(params = "id", method = RequestMethod.GET)
	public ResponseEntity<Product> search(@RequestParam("id") int id){
		
		Product product=productservice.findById(id).get();
		return new ResponseEntity<Product>(product,HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getAll(){
		try {
		List<Product> list=productservice.findAll();
		return new ResponseEntity<List<Product>>(list,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Product>>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
		}
	}
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<Product> add( @RequestBody Product product){
		try {	
			productservice.save(product);
			return new ResponseEntity<Product>(product,HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Product> edit(@RequestBody Product product,@PathVariable int id){
		Product x=productservice.getById(id);
		x.setDescription(product.getDescription());
		x.setImg(product.getImg());
		x.setName(product.getName());
		x.setPrice(product.getPrice());
		productservice.save(x);
		return new ResponseEntity(HttpStatus.OK);
	}
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Product> edit(@PathVariable int id){
		Product x=productservice.findById(id).get();
		List<CartItem> list1= cartitemservice.findAll();
		List<OrderItem> list2= orderitemservice.findAll();
		for (int i=0;i<list1.size();i++) {
			if (list1.get(i).getProduct().getId()==x.getId()) {
				cartitemservice.delete(list1.get(i));
			}
		}
		for (int i=0;i<list2.size();i++) {
			if (list2.get(i).getProduct().getId()==x.getId()) {
				orderitemservice.delete(list2.get(i));
			}
		}
		productservice.deleteById(id);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	
}

