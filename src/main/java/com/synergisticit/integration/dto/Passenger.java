package com.synergisticit.integration.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Passenger {

	private Long passengerId;
	private String name;
	private String gender;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dob;
	private String mobile;
	private String email;
	
	private Long ticketNumber;
	
	private Ticket ticket;
}
