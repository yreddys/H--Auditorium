package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.entity.Bus;

public interface BusService {

	List<Bus> searchBuses(String from, String to, LocalDate date);

	

}
