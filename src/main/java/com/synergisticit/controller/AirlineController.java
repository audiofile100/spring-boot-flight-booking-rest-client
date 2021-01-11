package com.synergisticit.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synergisticit.integration.AirlineRestClient;
import com.synergisticit.integration.dto.Airline;

@RestController
public class AirlineController {

	private AirlineRestClient airlineClient;
	
	public AirlineController(AirlineRestClient airlineClient) {
		this.airlineClient = airlineClient;
	}
	
	
	@GetMapping(value = "/airlines", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Airline>> findAirlines() {
		
		return new ResponseEntity<List<Airline>>(airlineClient.findAll(), HttpStatus.FOUND);
	}
	
	@GetMapping(value = "/airline", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Airline> findAirline(@RequestParam long id) {
		
		return airlineClient.findById(id);
	}
	
	@RequestMapping(value = "/saveAirline", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Airline> saveAirline(@RequestParam long id, 
			 									@RequestParam String code,
			 									@RequestParam String name) {
		
		return new ResponseEntity<Airline>(airlineClient.save(id, code, name), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/updateAirline", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Airline> updateAirport(@RequestParam long id, 
												 @RequestParam String code,
												 @RequestParam String name) {
		
		return new ResponseEntity<Airline>(airlineClient.updateById(id, code, name), HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/deleteAirline")
	public ResponseEntity<String> deleteAirline(@RequestParam long id) {
		
		return airlineClient.deleteById(id);
	}
}
