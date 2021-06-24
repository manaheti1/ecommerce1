package com.example.demo.Model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cart implements Serializable	{
	private static final long serialVersionUID = 3L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@OneToOne
	@JoinColumn(name = "userid")
	User user;
	public Cart() {
		this.id=0;
	}
	
	



public Cart(int id) {
	super();
	this.id = id;
}

public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
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
