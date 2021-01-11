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
import com.synergisticit.utilities.RestClientUtilities;

//@Slf4j
@Component
public class RoleRestClientImpl implements RoleRestClient {

	private static final String ROLE_URL = "http://localhost:8080/api/role/";
	
	private RestClientUtilities util;
	private RestTemplate restTemplate;
	
	
	public RoleRestClientImpl(RestClientUtilities util, 
								RestTemplate restTemplate) {
		this.util = util;
		this.restTemplate = restTemplate;
	}
	
	
	@Override
	public Role save(long roleId, String roleName) {
		
		Role role = new Role();
		role.setRoleId(roleId);
		role.setRoleName(roleName);
		
		HttpEntity<Role> requestBody = new HttpEntity<Role>(role, util.getJsonHeaders());
		
		return restTemplate.postForObject(ROLE_URL, requestBody, Role.class);
	}
	
	@Override
	public List<Role> findAll() {
		
		return Arrays.asList(restTemplate.getForObject(ROLE_URL, Role[].class));
	}
	
	@Override
	public ResponseEntity<Role> findById(long roleId) {
		
		HttpEntity<Role> requestEntity = new HttpEntity<Role>(util.getJsonHeaders());
		
		return restTemplate.exchange(ROLE_URL + roleId, HttpMethod.GET, requestEntity, Role.class);
	}

	@Override
	public Role updateById(long roleId, String roleName) {

		Role role = findById(roleId).getBody();
		role.setRoleName(roleName);
		
		HttpEntity<Role> requestBody = new HttpEntity<Role>(role, util.getJsonHeaders());
		
		restTemplate.put(ROLE_URL, requestBody, Role.class);
		
		return restTemplate.getForObject(ROLE_URL + roleId, Role.class);
	}

	@Override
	public ResponseEntity<String> deleteById(long roleId) {
		
		restTemplate.delete(ROLE_URL + roleId);
		
		return new ResponseEntity<String>("role with id: " + roleId + " was deleted", HttpStatus.GONE);  
	}
}
