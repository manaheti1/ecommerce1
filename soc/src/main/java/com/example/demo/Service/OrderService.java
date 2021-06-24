package com.example.demo.Service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Orders;
import com.example.demo.Model.User;



@Service
public interface OrderService extends JpaRepository<Orders, Integer>{
	List<Orders>
	findAllByUser(User user);
}
