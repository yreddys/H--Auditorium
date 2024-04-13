package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int number;
    private boolean available;

    @ManyToOne
    @JoinColumn(name = "row_id") // This should match the column name in the database
    private SeatRow row;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public SeatRow getRow() {
		return row;
	}

	public void setRow(SeatRow row) {
		this.row = row;
	}

	public Seat() {
    
    }

	public Seat(Long id, int number, boolean available, SeatRow row) {
		
		this.id = id;
		this.number = number;
		this.available = available;
		this.row = row;
	}

	
    

    
}
