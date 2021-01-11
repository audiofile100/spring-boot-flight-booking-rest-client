package com.synergisticit.integration;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.synergisticit.integration.dto.Airport;
import com.synergisticit.utilities.RestClientUtilities;

@Component
public class AirportRestClientImpl implements AirportRestClient {

	private static final String AIRPORT_URL = "http://localhost:8080/api/airport/";
	
	private RestClientUtilities util;
	private RestTemplate restTemplate;
	
	
	public AirportRestClientImpl(RestClientUtilities util, 
								RestTemplate restTemplate) {
		this.util = util;
		this.restTemplate = restTemplate;
	}
	
	
	
	@Override
	public Airport save(long airportId, String airportName, String airportCode, String airportCity,
							String airportState) {
		
		Airport airport = new Airport();
		airport.setAirportId(airportId);
		airport.setAirportName(airportName);
		airport.setAirportCode(airportCode);
		airport.setAirportCity(airportCity);
		airport.setAirportState(airportState);
		
		HttpEntity<Airport> requestBody = new HttpEntity<>(airport, util.getJsonHeaders());
		
		return restTemplate.postForObject(AIRPORT_URL, requestBody, Airport.class);
	}
	
	@Override
	public List<Airport> findAll() {
		
		return Arrays.asList(restTemplate.getForObject(AIRPORT_URL, Airport[].class));
	}

	@Override
	public ResponseEntity<Airport> findById(long airportId) {
		
		HttpEntity<Airport> requestEntity = new HttpEntity<>(util.getJsonHeaders());
		
		return restTemplate.exchange(AIRPORT_URL + airportId, HttpMethod.GET, requestEntity, Airport.class);
	}

	@Override
	public Airport updateById(long airportId, String airportName, String airportCode, String airportCity, String airportState) {
		
		Airport airport = findById(airportId).getBody();
		airport.setAirportName(airportName);
		airport.setAirportCode(airportCode);
		airport.setAirportCity(airportCity);
		airport.setAirportState(airportState);
		
		HttpEntity<Airport> requestBody = new HttpEntity<>(airport, util.getJsonHeaders());
		
		restTemplate.put(AIRPORT_URL, requestBody, Airport.class);
		
		return restTemplate.getForObject(AIRPORT_URL + airport.getAirportId(), Airport.class);
	}

	@Override
	public ResponseEntity<String> deleteById(long airportId) {
		
		restTemplate.delete(AIRPORT_URL + airportId);
		
		return new ResponseEntity<String>("airport with id: " + airportId + " was deleted", HttpStatus.GONE); 
	}
}
