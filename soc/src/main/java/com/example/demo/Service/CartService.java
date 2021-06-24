package com.example.demo.Service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Cart;



@Service
public interface CartService extends JpaRepository<Cart, Integer>{
	Optional<Cart> findByUserId(int id);
}
