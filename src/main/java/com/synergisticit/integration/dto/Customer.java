package com.synergisticit.integration.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Customer {

	private Long customerId;
	private String name;
	private Address customerAddress;
	private String phone;
	private String email;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dob;
	private String ssn;
	private String gender;
	
	private User user;
}
