package com.synergisticit.integration.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Flight {

	private Long flightId;
	private String flightNumber;
	private Airline airline;
	private Airport departFrom;
	private Airport arriveAt;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime departureDateTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime arrivalDateTime;
	
	private PrettyFlight prettyFlight;
	
	private Long price;
}
