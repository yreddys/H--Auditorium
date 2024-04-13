package com.example.demo.service;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Bus;
import com.example.demo.repository.BusRepository;

@Service
public class BusServiceImpl implements BusService {

    @Autowired
    private BusRepository busRepository;

    @Override
    public List<Bus> searchBuses(String from, String to, LocalDate date) {
        // Query the database for available buses based on the provided parameters
        return busRepository.findByFromLocationAndToLocationAndDeparturedate(from, to, date);
    }
}



	



	


	



