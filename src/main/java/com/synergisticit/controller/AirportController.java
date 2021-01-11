package com.synergisticit.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synergisticit.integration.AirportRestClient;
import com.synergisticit.integration.dto.Airport;

@RestController
public class AirportController {

	private AirportRestClient airportClient;
	
	public AirportController(AirportRestClient airportClient) {
		this.airportClient = airportClient;
	}
	
	
	@GetMapping(value = "/airports", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Airport>> findAirports() {
		
		return new ResponseEntity<List<Airport>>(airportClient.findAll(), HttpStatus.FOUND);
	}
	
	@GetMapping(value = "/airport", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Airport> findAirport(@RequestParam long id) {
		
		return airportClient.findById(id);
	}
	
	@RequestMapping(value = "/saveAirport", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Airport> saveAirport(@RequestParam long id, 
												 @RequestParam String name,
												 @RequestParam String code,
												 @RequestParam String city,
												 @RequestParam String state) {
		
		return new ResponseEntity<Airport>(airportClient.save(id, name, code, city, state), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/updateAirport", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Airport> updateAirport(@RequestParam long id, 
												 @RequestParam String name,
												 @RequestParam String code,
												 @RequestParam String city,
												 @RequestParam String state) {
		
		return new ResponseEntity<Airport>(airportClient.updateById(id, name, code, city, state), HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/deleteAirport")
	public ResponseEntity<String> deleteAirport(@RequestParam long id) {
		
		return airportClient.deleteById(id);
	}
}
