package com.synergisticit.integration;

import com.synergisticit.integration.dto.Passenger;
import com.synergisticit.integration.dto.Payment;
import com.synergisticit.integration.dto.Ticket;

public interface BookingRestClient {

	void save(Passenger passenger);
	
	Ticket purchase(Payment payment, long customerId, long flightId);
}
