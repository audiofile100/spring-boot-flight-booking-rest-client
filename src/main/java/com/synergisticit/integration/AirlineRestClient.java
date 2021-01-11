package com.synergisticit.integration;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.synergisticit.integration.dto.Airline;

public interface AirlineRestClient {

	Airline save(long airlineId, String airlineCode, String airlineName);
	
	List<Airline> findAll();
	ResponseEntity<Airline> findById(long airlineId);
	
	Airline updateById(long airlineId, String airlineCode, String airlineName);
	
	ResponseEntity<String> deleteById(long airlineId);
}
