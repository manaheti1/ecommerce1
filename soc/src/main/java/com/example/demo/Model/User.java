package com.example.demo.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User implements Serializable	{
	private static final long serialVersionUID = 2L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@Column(name="username")
	String username;
	@Column(name="password")
	String password;
	@Column(name="role")
	String role;
	@Column(name="address")
	String address;
	
	public User() {
		this.id=0;

	}

	public User(String username, String password) {
		
		this.username = username;
		this.password = password;

	}
	
	
	public User(int id, String username, String password, String role, String address) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.address = address;
	}
	

	

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}
	
    public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
