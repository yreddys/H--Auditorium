package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Testingcontroller {
	@GetMapping("/logging")
	String login() {
		return "newfeaturesupdation";
	}

	@GetMapping("/mybooking")
	String booking() {
		return "newfeaturesupdation";
	}
}
