package com.synergisticit.integration;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.synergisticit.integration.dto.Airport;

public interface AirportRestClient {

	Airport save(long airportId, String airportName, String airportCode, String airportCity, String airportState);
	
	List<Airport> findAll();
	ResponseEntity<Airport> findById(long airportId);
	
	Airport updateById(long airportId, String airportName, String airportCode, String airportCity, String airportState);
	
	ResponseEntity<String> deleteById(long airportId);
}
