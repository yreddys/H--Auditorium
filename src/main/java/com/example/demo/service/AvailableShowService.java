package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.AvailableShow;

public interface AvailableShowService {

	AvailableShow saveData(AvailableShow availablebus);

	List<AvailableShow> findAllUsers();

	AvailableShow findByShowName(String showname);

	

	

}
