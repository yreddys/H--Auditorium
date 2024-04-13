package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Bus;

@Repository
public interface BusRepository extends JpaRepository<Bus,Integer>{

	List<Bus> findByFromLocationAndToLocationAndDeparturedate(String from, String to, LocalDate date);

	
	

}
