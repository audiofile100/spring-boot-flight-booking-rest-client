package com.synergisticit.integration;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.synergisticit.integration.dto.Airline;
import com.synergisticit.utilities.RestClientUtilities;

@Component
public class AirlineRestClientImpl implements AirlineRestClient {
	
	private static final String AIRLINE_URL = "http://localhost:8080/api/airline/";
	
	private RestClientUtilities util;
	private RestTemplate restTemplate;
	
	
	public AirlineRestClientImpl(RestClientUtilities util, 
								RestTemplate restTemplate) {
		this.util = util;
		this.restTemplate = restTemplate;
	}
	
	
	
	
	@Override
	public Airline save(long airlineId, String airlineCode, String airlineName) {
		
		Airline airline = new Airline();
		airline.setAirlineId(airlineId);
		airline.setAirlineCode(airlineCode);
		airline.setAirlineName(airlineName);
		
		HttpEntity<Airline> requestBody = new HttpEntity<>(airline, util.getJsonHeaders());
		
		return restTemplate.postForObject(AIRLINE_URL, requestBody, Airline.class);
	}
	
	@Override
	public List<Airline> findAll() {
		
		return Arrays.asList(restTemplate.getForObject(AIRLINE_URL, Airline[].class));
	}

	@Override
	public ResponseEntity<Airline> findById(long airlineId) {
		
		HttpEntity<Airline> requestEntity = new HttpEntity<>(util.getJsonHeaders());
		
		return restTemplate.exchange(AIRLINE_URL + airlineId, HttpMethod.GET, requestEntity, Airline.class);
	}

	@Override
	public Airline updateById(long airlineId, String airlineCode, String airlineName) {
		
		Airline airline = findById(airlineId).getBody();
		airline.setAirlineCode(airlineCode);
		airline.setAirlineName(airlineName);
		
		HttpEntity<Airline> requestBody = new HttpEntity<>(airline, util.getJsonHeaders());
		
		restTemplate.put(AIRLINE_URL, requestBody, Airline.class);
		
		return restTemplate.getForObject(AIRLINE_URL + airline.getAirlineId(), Airline.class);
	}

	@Override
	public ResponseEntity<String> deleteById(long airlineId) {
		
		restTemplate.delete(AIRLINE_URL + airlineId);
		
		return new ResponseEntity<String>("airline with id: " + airlineId + " was deleted", HttpStatus.GONE);  
	}

}
