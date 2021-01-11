package com.synergisticit.integration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.synergisticit.integration.dto.Flight;
import com.synergisticit.utilities.RestClientUtilities;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FlightRestClientImpl implements FlightRestClient {

	private static final String FLIGHT_URL = "http://localhost:8080/api/flight/";
	
	private RestClientUtilities util;
	private RestTemplate restTemplate;
	
	
	public FlightRestClientImpl(RestClientUtilities util, 
								RestTemplate restTemplate) {
		this.util = util;
		this.restTemplate = restTemplate;
	}
	
	
	@Override
	public Flight save(Flight flight) {
		
		HttpEntity<Flight> requestBody = new HttpEntity<>(flight, util.getJsonHeaders());
		
		return restTemplate.postForObject(FLIGHT_URL, requestBody, Flight.class);
	}

	@Override
	public List<Flight> findAll() {
		
		return Arrays.asList(restTemplate.getForObject(FLIGHT_URL, Flight[].class));
	}

	@Override
	public ResponseEntity<Flight> findById(long flightId) {
		
		HttpEntity<Flight> requestEntity = new HttpEntity<>(util.getJsonHeaders());
		
		return restTemplate.exchange(FLIGHT_URL + flightId, HttpMethod.GET, requestEntity, Flight.class);
	}

	@Override
	public Flight updateById(long flightId, LocalDateTime departDateTime, LocalDateTime arriveDateTime, long price) {
		
		Flight flight = findById(flightId).getBody();
		flight.setDepartureDateTime(departDateTime);
		flight.setArrivalDateTime(arriveDateTime);
		flight.setPrice(price);
		
		HttpEntity<Flight> requestBody = new HttpEntity<>(flight, util.getJsonHeaders());
		
		restTemplate.put(FLIGHT_URL, requestBody, Flight.class);
		
		return restTemplate.getForObject(FLIGHT_URL + flight.getFlightId(), Flight.class);
	}

	@Override
	public ResponseEntity<String> deleteById(long flightId) {
		
		restTemplate.delete(FLIGHT_URL + flightId);
		
		return new ResponseEntity<String>("flight with id: " + flightId + " was deleted", HttpStatus.GONE);  
	}

	@Override
	public List<Flight> searchFlights(String fromAirportCode, String toAirportCode, LocalDate departDate, long tickets) {
		
		String FLIGHT_SEARCH_API = FLIGHT_URL + "search?departCode=" + fromAirportCode + "&arriveCode=" + toAirportCode + "&depart=" + departDate + "&tickets=" + tickets;
		log.debug("search url: " + FLIGHT_SEARCH_API);
		return Arrays.asList(restTemplate.getForObject(FLIGHT_SEARCH_API, Flight[].class));
	}

}
