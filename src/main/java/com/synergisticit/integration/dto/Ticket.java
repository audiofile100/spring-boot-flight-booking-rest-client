package com.synergisticit.integration.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
public class Ticket {

	private Long ticketId;
	private Customer customer;
	private Flight flight;
	private Long total;
	
	@ToString.Exclude
	private List<Passenger> passengers = new ArrayList<>();
}
