package com.synergisticit.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.synergisticit.integration.BookingRestClient;
import com.synergisticit.integration.FlightRestClient;
import com.synergisticit.integration.dto.Passenger;
import com.synergisticit.integration.dto.Payment;
import com.synergisticit.integration.dto.Ticket;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BookingController {

	private BookingRestClient bookingClient;
	private FlightRestClient flightClient;
	
	public BookingController(BookingRestClient bookingClient,
								FlightRestClient flightClient) {
		this.bookingClient = bookingClient;
		this.flightClient = flightClient;
	}
	
	
	// --- MAPPINGS ---
	
	@GetMapping("/booking")
	public String booking(@RequestParam long customerId, @RequestParam long tickets, @RequestParam long flightId, Model model, HttpSession session) {
		
		long total = flightClient.findById(flightId).getBody().getPrice() * tickets;
		
		model.addAttribute("tickets", tickets);
		model.addAttribute("flightId", flightId);
		model.addAttribute("total", total);
		
		session.setAttribute("customerId", customerId);
		session.setAttribute("flightId", flightId);
		session.setAttribute("tickets", tickets);
		session.setAttribute("total", total);
		session.setAttribute("count", 0);
		
		return "booking";
	}
	
	@RequestMapping("/savePassenger")
	public String savePassenger(@ModelAttribute Passenger passenger, HttpSession session, Model model) {
		log.debug("BookingController.savePassenger().......");
		
		long remaining = (Long)session.getAttribute("tickets")-1L;
		session.setAttribute("tickets", remaining);
		model.addAttribute("tickets", remaining);
		
		bookingClient.save(passenger);
		
		int count = (int)session.getAttribute("count");
		session.setAttribute("count", ++count);
		
		model.addAttribute("passengerSaved", "Passenger "+count+" has been saved.");
		model.addAttribute("passenger", new Passenger());
		
		return "booking";
	}
	
	@RequestMapping(value = "/purchase", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Ticket> purchase(@ModelAttribute Payment payment, HttpSession session, Model model) {
		log.debug("BookingController.purchase().....");
		
		long customerId = (long) session.getAttribute("customerId");
		long flightId = (long) session.getAttribute("flightId");
		
		return new ResponseEntity<Ticket>(bookingClient.purchase(payment, customerId, flightId), HttpStatus.OK);
	}
	
	@ModelAttribute
	public Payment payment() {
		return new Payment();
	}
	
	@ModelAttribute
	public Passenger passenger() {
		return new Passenger();
	}
}
