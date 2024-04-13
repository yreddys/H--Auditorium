package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.example.demo.entity.AvailableShow;
import com.example.demo.service.AvailableShowService;

@Controller
public class AvailableShowController {

	@Autowired
	private AvailableShowService availablebusservice;

	@GetMapping("/addingbus")
	public String saveData(Model model) {
		model.addAttribute("availablebus", new AvailableShow());
		return "busaddingform";
	}

	@PostMapping("/addingbus")
	public String savingData(@ModelAttribute("availablebus") AvailableShow availablebus, BindingResult bindingResult,
			Model model) {
		availablebusservice.saveData(availablebus);
		model.addAttribute("availablebus", availablebus);
		return "busaddingsuccess";
	}

	@GetMapping("/Available/Report/All")
	public String listRegisteredUsers(Model model) {
		List<AvailableShow> users = availablebusservice.findAllUsers();
		model.addAttribute("users", users);
		return "AvailablebusDisplayByID";
	}

	@PostMapping("/checkingBusesAvailability")
	public String checkBusAvailability(@RequestParam String showname, Model model) {
		AvailableShow registration = availablebusservice.findByShowName(showname);

		if (registration != null) {
			// Bus availability found, redirect to the appropriate page
			return "redirect:/Available/Report/All";
		} else {
			// Bus availability not found, show error message in the current view
			model.addAttribute("error", "Buses Not Available for the provided start point");
			return "redirect:/Available/Report/All"; // Assuming "bus" is the view name for displaying bus availability
		}
	}

}
