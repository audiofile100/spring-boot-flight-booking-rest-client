package com.synergisticit.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synergisticit.integration.CustomerRestClient;
import com.synergisticit.integration.dto.Customer;

@RestController
public class CustomerController {

	private CustomerRestClient customerClient;
	
	public CustomerController(CustomerRestClient customerClient) {
		this.customerClient = customerClient;
	}
	
	
	@GetMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Customer>> findCustomers() {
		
		return new ResponseEntity<List<Customer>>(customerClient.findAll(), HttpStatus.FOUND);
	}
	
	@GetMapping(value = "/customer", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> findCustomer(@RequestParam long id) {
		
		return customerClient.findById(id);
	}
	
	@RequestMapping(value = "/saveCustomer", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> saveCustomer(@ModelAttribute Customer customer, Model model) {
		
		return new ResponseEntity<Customer>(customerClient.save(customer), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/updateCustomerContact", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> updateCustomerContact(@RequestParam long id, 
												 			@RequestParam String addressLine1,
												 			@RequestParam String addressLine2,
												 			@RequestParam String city,
												 			@RequestParam String state,
												 			@RequestParam String phone,
												 			@RequestParam String email) {
		
		return new ResponseEntity<Customer>(customerClient.updateCustomerContact(id, 
																					addressLine1, 
																					addressLine2, 
																					city, state, phone, email), HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/deleteCustomer")
	public ResponseEntity<String> deleteCustomer(@RequestParam long id) {
		
		return customerClient.deleteById(id);
	}
}
