package com.synergisticit.integration.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Payment {

	private String name;
	
	private Address billingAddress;
	private String mobile;
	
	private String creditCardNumber;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate expiration;
	private String ccv;
	
	private Long total;
	
	private Long flightId;
}
