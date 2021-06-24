package com.example.demo.Util;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.Model.CartItem;
import com.example.demo.Model.OrderItem;

public class Convert {
public static List<OrderItem> convertCartItem(List<CartItem> a){
		List<OrderItem> b=new ArrayList<>();
		for (CartItem i:a) {
			b.add(new OrderItem(0,i.getAmount(),i.getProduct()));
		}
		return b;
	}
}
