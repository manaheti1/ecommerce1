package com.example.demo.Service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.demo.Model.OrderItem;
import com.example.demo.Model.Orders;



@Service
public interface OrderItemService extends JpaRepository<OrderItem, Integer>{
	List<OrderItem> findAllByOrder(Orders order);
}
