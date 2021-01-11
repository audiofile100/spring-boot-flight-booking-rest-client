package com.synergisticit.integration;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.synergisticit.integration.dto.Role;
import com.synergisticit.integration.dto.User;
import com.synergisticit.utilities.RestClientUtilities;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserRestClientImpl implements UserRestClient {

	private static final String USER_URL = "http://localhost:8080/api/user/";
	
	private RestClientUtilities util;
	private RestTemplate restTemplate;
	private RoleRestClient roleClient;
	
	
	public UserRestClientImpl(RestClientUtilities util, 
								RestTemplate restTemplate,
								RoleRestClient roleClient) {
		this.util = util;
		this.restTemplate = restTemplate;
		this.roleClient = roleClient;
	}
	
	
	@Override
	public User save(long userId, String username, String password, String email, List<Long> roleId) {
		log.debug("UserRestClientImpl.save()......");
		
		User user = new User();
		user.setUserId(userId);
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		
		for (Long rId : roleId) {
			Role role = roleClient.findById(rId).getBody();
			user.getRoles().add(role);
		}
		
		HttpEntity<User> requestBody = new HttpEntity<User>(user, util.getJsonHeaders());
		
		return restTemplate.postForObject(USER_URL, requestBody, User.class);
	}

	@Override
	public List<User> findAll() {
		
		return Arrays.asList(restTemplate.getForObject(USER_URL, User[].class));
	}

	@Override
	public ResponseEntity<User> findById(long userId) {
		
		HttpEntity<User> requestEntity = new HttpEntity<>(util.getJsonHeaders());
		
		return restTemplate.exchange(USER_URL + userId, HttpMethod.GET, requestEntity, User.class);
	}

	@Override
	public User updateById(long userId, String password, String email, List<Long> roleId) {
		
		User user = findById(userId).getBody();
		user.setPassword(password);
		user.setEmail(email);
		
		for (Long rId : roleId) {
			Role role = roleClient.findById(rId).getBody();
			if (!user.getRoles().contains(role))
				user.getRoles().add(role);
		}
		
		HttpEntity<User> request = new HttpEntity<>(user, util.getJsonHeaders());
		
		restTemplate.put(USER_URL, request, User.class);
		
		return restTemplate.getForObject(USER_URL + userId, User.class);
	}

	@Override
	public ResponseEntity<String> deleteById(long userId) {
		
		restTemplate.delete(USER_URL + userId);
		
		return new ResponseEntity<String>("user with id: " + userId + " was deleted", HttpStatus.GONE);  
	}

}
