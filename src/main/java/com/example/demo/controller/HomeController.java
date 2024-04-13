package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@GetMapping("/home")
	String home() {
		return "homePage";
	}

	@GetMapping("/adminhome")
	String adminhome() {
		return "adminhomePage";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false); // Get the existing session, if it exists
		if (session != null) {
			session.invalidate(); // Invalidate the session, clearing all attributes
		}
		return "redirect:/home"; // Redirect the user to the home page after logout
	}

}
