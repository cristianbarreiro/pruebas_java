package org.learnquest;

import org.springframework.stereotype.Component;

@Component
public class Laptop {
	private int id; 
	private String brand;
	
	public Laptop(int id, String brand) {
		super();
		this.id = id;
		this.brand = brand;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public Laptop() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setBrand(String brand) {
		this.brand = brand;
	} 
}
