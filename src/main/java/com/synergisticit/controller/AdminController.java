package com.synergisticit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.synergisticit.integration.dto.Customer;
import com.synergisticit.integration.dto.Flight;

@Controller
public class AdminController {

	
	@GetMapping(value = { "/", "/home", "/admin" })
	public String getForm() {
		
		return "admin";
	}
	
		
	@ModelAttribute
	public Flight flight() {
		return new Flight();
	}
	
	@ModelAttribute
	public Customer customer() {
		return new Customer();
	}
}
