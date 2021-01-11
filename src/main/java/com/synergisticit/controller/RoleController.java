package com.synergisticit.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synergisticit.integration.RoleRestClient;
import com.synergisticit.integration.dto.Role;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RoleController {

	private RoleRestClient roleClient;
	
	public RoleController(RoleRestClient roleClient) {
		this.roleClient = roleClient;
	}
	
	
	@GetMapping(value = "/roles", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Role>> findRoles() {
		
		return new ResponseEntity<List<Role>>(roleClient.findAll(), HttpStatus.FOUND);
	}
	
	@GetMapping(value = "/role", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findRole(@RequestParam long id) {
		
		return roleClient.findById(id);
	}
	
	@RequestMapping(value = "/saveRole", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Role> saveRole(@RequestParam long id, @RequestParam String name) {
		
		return new ResponseEntity<Role>(roleClient.save(id, name), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/updateRole", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Role> updateRole(@RequestParam int id, @RequestParam String name) {
		log.debug("RoleController.updateRole().......");
		
		return new ResponseEntity<Role>(roleClient.updateById(id, name), HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/deleteRole")
	public ResponseEntity<String> deleteRole(@RequestParam long id) {
		
		return roleClient.deleteById(id);
	}
}
