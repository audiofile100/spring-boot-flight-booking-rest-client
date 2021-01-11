package com.synergisticit.integration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import com.synergisticit.integration.dto.Flight;

public interface FlightRestClient {

	Flight save(Flight flight);
	
	List<Flight> findAll();
	ResponseEntity<Flight> findById(long flightId);
	
	Flight updateById(long flightId, LocalDateTime departDateTime, LocalDateTime arriveDateTime, long price);
	
	ResponseEntity<String> deleteById(long flightId);
	
	
	List<Flight> searchFlights(@RequestParam String fromAirportCode, @RequestParam String toAirportCode, @RequestParam LocalDate departDate, @RequestParam long tickets);
}
