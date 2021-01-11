package com.synergisticit.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synergisticit.integration.FlightRestClient;
import com.synergisticit.integration.dto.Flight;

//import lombok.extern.slf4j.Slf4j;

//@Slf4j
@RestController
public class FlightController {

	private FlightRestClient flightClient;
	
	public FlightController(FlightRestClient flightClient) {
		this.flightClient = flightClient;
	}
	
	
	@GetMapping(value = "/flights", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Flight>> findFlights() {
		
		return new ResponseEntity<List<Flight>>(flightClient.findAll(), HttpStatus.FOUND);
	}
	
	@GetMapping(value = "/flight", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Flight> findFlight(@RequestParam long id) {
		
		return flightClient.findById(id);
	}
	
	@GetMapping(value = "/flight/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Flight>> searchFlights(@RequestParam String fromAirportCode,
												@RequestParam String toAirportCode,
												@RequestParam String departDate,
												@RequestParam long tickets) {
		
		LocalDate depart = LocalDate.parse(departDate);
		
		return new ResponseEntity<List<Flight>>(flightClient.searchFlights(fromAirportCode, toAirportCode, depart, tickets), HttpStatus.FOUND);
	}
	
	@RequestMapping(value = "/saveFlight", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Flight> saveFlight(@ModelAttribute Flight flight, Model model) {
		
		return new ResponseEntity<Flight>(flightClient.save(flight), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/updateFlight", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Flight> updateFlight(@RequestParam long id, 
												@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime departDateTime, 
												@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime arriveDateTime, 
												@RequestParam long price) {
		
		return new ResponseEntity<Flight>(flightClient.updateById(id, departDateTime, arriveDateTime, price), HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/deleteFlight")
	public ResponseEntity<String> deleteFlight(@RequestParam long id) {
		
		return flightClient.deleteById(id);
	}
}
