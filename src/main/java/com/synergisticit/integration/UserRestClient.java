package com.synergisticit.integration;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.synergisticit.integration.dto.User;

public interface UserRestClient {

	User save(long userId, String username, String password, String email, List<Long> roleId);
	
	List<User> findAll();
	ResponseEntity<User> findById(long userId);
	
	User updateById(long userId, String password, String email, List<Long> roleId);
	
	ResponseEntity<String> deleteById(long userId);
}
