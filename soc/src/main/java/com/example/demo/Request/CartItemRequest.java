package com.example.demo.Request;

import java.io.Serializable;

public class CartItemRequest implements Serializable{
	private static final long serialVersionUID = 2L;
	int id;
	int userid;
	int productid;
	int amount;
	
	
	public CartItemRequest(int id, int userid, int productid, int amount) {
		super();
		this.id = id;
		this.userid = userid;
		this.productid = productid;
		this.amount = amount;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getUserid() {
		return userid;
	}


	public void setUserid(int userid) {
		this.userid = userid;
	}


	public int getProductid() {
		return productid;
	}


	public void setProductid(int productid) {
		this.productid = productid;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public CartItemRequest() {
		
	}
	
	

	
	
}
