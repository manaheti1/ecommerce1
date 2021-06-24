package com.example.demo.Service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Product;



@Service
public interface ProductService extends JpaRepository<Product, Integer>{
	
}
