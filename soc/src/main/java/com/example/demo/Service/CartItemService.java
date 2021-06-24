package com.example.demo.Service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Cart;
import com.example.demo.Model.CartItem;



@Service
public interface CartItemService extends JpaRepository<CartItem, Integer>{
	List<CartItem> findAllByCart(Cart cart);
	void deleteByCartId(int id);
}
