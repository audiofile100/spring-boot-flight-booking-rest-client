package com.synergisticit.integration;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.synergisticit.integration.dto.Passenger;
import com.synergisticit.integration.dto.Payment;
import com.synergisticit.integration.dto.Ticket;
import com.synergisticit.utilities.RestClientUtilities;

//import lombok.extern.slf4j.Slf4j;

//@Slf4j
@Component
public class BookingRestClientImpl implements BookingRestClient {

	private static final String BOOKING_URL = "http://localhost:8080/api/booking/";
	
	private RestClientUtilities util;
	private RestTemplate restTemplate;
	
	public BookingRestClientImpl(RestClientUtilities util,
									RestTemplate restTemplate) {
		this.util = util;
		this.restTemplate = restTemplate;
	}
	
	
	@Override
	public void save(Passenger passenger) {
		
		HttpEntity<Passenger> requestBody = new HttpEntity<>(passenger, util.getJsonHeaders());
		
		restTemplate.postForObject(BOOKING_URL + "passenger", requestBody, Passenger.class);
	}


	@Override
	public Ticket purchase(Payment payment, long customerId, long flightId) {
		String PURCHASE_URL = BOOKING_URL + "purchase?customerId=" + customerId + "&flightId=" + flightId;
		
		HttpEntity<Payment> requestBody = new HttpEntity<>(payment, util.getJsonHeaders());
		
		return restTemplate.postForObject(PURCHASE_URL, requestBody, Ticket.class);
	}

}
