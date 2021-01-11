package com.synergisticit.integration.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
public class Airport {

	private Long airportId;
	private String airportName;
	private String airportCode;
	private String airportCity;
	private String airportState;
	
	@ToString.Exclude
	private List<Airport> flights = new ArrayList<>();
}
