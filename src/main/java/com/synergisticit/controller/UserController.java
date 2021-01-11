package com.synergisticit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synergisticit.integration.UserRestClient;
import com.synergisticit.integration.dto.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserController {

	@Autowired
	private UserRestClient userClient;
	
	@GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> findUsers() {
		
		return new ResponseEntity<List<User>>(userClient.findAll(), HttpStatus.FOUND);
	}
	
	@GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findUser(@RequestParam long id) {
		
		return userClient.findById(id);
	}
	
	@RequestMapping(value = "/saveUser", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> saveUser(@RequestParam long id, 
											@RequestParam String username, 
											@RequestParam String password, 
											@RequestParam String email, 
											@RequestParam List<Long> roleId) {
		log.debug("UserController.saveUser().....");
				
		return new ResponseEntity<User>(userClient.save(id, username, password, email, roleId), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/updateUser", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updateUser(@RequestParam long id, 
											@RequestParam String password, 
											@RequestParam String email,
											@RequestParam List<Long> roleId) {
		
		return new ResponseEntity<User>(userClient.updateById(id, password, email, roleId), HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/deleteUser")
	public ResponseEntity<String> deleteUser(@RequestParam long id) {
		
		return userClient.deleteById(id);
	}
}
