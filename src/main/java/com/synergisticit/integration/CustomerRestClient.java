package com.synergisticit.integration;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.synergisticit.integration.dto.Customer;

public interface CustomerRestClient {

	Customer save(Customer customer);
	
	List<Customer> findAll();
	ResponseEntity<Customer> findById(long customerId);
	
	Customer updateCustomerContact(long customerId, String addressLine1, String addressLine2, String city, String state, String phone, String email);
	
	ResponseEntity<String> deleteById(long customerId);
}
