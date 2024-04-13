package com.example.demo.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class AvailableShow {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String showID;
	private String showName;
	private Date showDate;
	private String time;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getShowID() {
		return showID;
	}
	public void setShowID(String showID) {
		this.showID = showID;
	}
	public String getShowName() {
		return showName;
	}
	public void setShowName(String showName) {
		this.showName = showName;
	}
	public Date getShowDate() {
		return showDate;
	}
	public void setShowDate(Date showDate) {
		this.showDate = showDate;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public AvailableShow(long id, String showID, String showName, Date showDate, String time) {
		
		this.id = id;
		this.showID = showID;
		this.showName = showName;
		this.showDate = showDate;
		this.time = time;
	}
	
	public AvailableShow() {}
}