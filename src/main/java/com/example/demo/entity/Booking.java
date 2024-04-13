package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity

public class Booking {
	@Id
	@GeneratedValue
	private int id;
	private int seatNumber;
	private String name;
	private int age;
	private String mobile;
	private String email;
	private double fare;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getFare() {
		return fare;
	}
	public void setFare(double fare) {
		this.fare = fare;
	}
	public Booking( int seatNumber, String name, int age, String mobile, String email, double fare) {
		
		
		this.seatNumber = seatNumber;
		this.name = name;
		this.age = age;
		this.mobile = mobile;
		this.email = email;
		this.fare = fare;
	}
	public Booking() {}
	
	
	
}
