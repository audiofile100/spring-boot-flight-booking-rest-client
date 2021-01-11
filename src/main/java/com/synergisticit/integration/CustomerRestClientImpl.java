package com.synergisticit.integration;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.synergisticit.integration.dto.Address;
import com.synergisticit.integration.dto.Customer;
import com.synergisticit.utilities.RestClientUtilities;

@Component
public class CustomerRestClientImpl implements CustomerRestClient {

private static final String CUSTOMER_URL = "http://localhost:8080/api/customer/";
	
	private RestClientUtilities util;
	private RestTemplate restTemplate;
	
	
	public CustomerRestClientImpl(RestClientUtilities util, 
								RestTemplate restTemplate) {
		this.util = util;
		this.restTemplate = restTemplate;
	}
	
	
	
	@Override
	public Customer save(Customer customer) {
		
		HttpEntity<Customer> requestBody = new HttpEntity<>(customer, util.getJsonHeaders());
		
		return restTemplate.postForObject(CUSTOMER_URL, requestBody, Customer.class);
	}

	@Override
	public List<Customer> findAll() {
		
		return Arrays.asList(restTemplate.getForObject(CUSTOMER_URL, Customer[].class));
	}

	@Override
	public ResponseEntity<Customer> findById(long customerId) {
		
		HttpEntity<Customer> requestEntity = new HttpEntity<>(util.getJsonHeaders());
		
		return restTemplate.exchange(CUSTOMER_URL + customerId, HttpMethod.GET, requestEntity, Customer.class);
	}

	@Override
	public Customer updateCustomerContact(long customerId, String addressLine1, String addressLine2, String city,
			String state, String phone, String email) {
		
		Customer customer = findById(customerId).getBody();
		
		Address a = new Address();
		a.setAddressLine1(addressLine1);
		a.setAddressLine2(addressLine2);
		a.setCity(city);
		a.setState(state);
		
		customer.setCustomerAddress(a);
		customer.setPhone(phone);
		customer.setEmail(email);
		
		HttpEntity<Customer> requestBody = new HttpEntity<>(customer, util.getJsonHeaders());
		
		restTemplate.put(CUSTOMER_URL, requestBody, Customer.class);
		
		return restTemplate.getForObject(CUSTOMER_URL + customerId, Customer.class);
	}

	@Override
	public ResponseEntity<String> deleteById(long customerId) {
		
		restTemplate.delete(CUSTOMER_URL + customerId);
		
		return new ResponseEntity<String>("customer with id: " + customerId + " was deleted", HttpStatus.GONE); 
	}
}
