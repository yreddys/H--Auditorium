package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.AvailableShow;
import com.example.demo.repository.AvailableShowRepository;

@Service
public class AvailableShowImpl implements AvailableShowService {
	@Autowired
	private AvailableShowRepository availablebusrepository;

	@Override
	public AvailableShow saveData(AvailableShow availablebus) {

		return availablebusrepository.save(availablebus);
	}

	@Override
	public List<AvailableShow> findAllUsers() {

		List<AvailableShow> users = availablebusrepository.findAll();
		return users.stream().map((user) -> convertEntityToDto(user)).collect(Collectors.toList());
	}

	private AvailableShow convertEntityToDto(AvailableShow user) {
	    AvailableShow userDto = new AvailableShow();

	    userDto.setId(user.getId());
	    userDto.setShowID(user.getShowID());
	    userDto.setShowName(user.getShowName());
	    userDto.setShowDate(user.getShowDate());
	    userDto.setTime(user.getTime());

	    return userDto;
	}


	@Override
	public AvailableShow findByShowName(String showname) {
		
		return availablebusrepository.findByShowName(showname);
	}

	
}
