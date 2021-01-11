package com.synergisticit.integration;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.synergisticit.integration.dto.Role;

public interface RoleRestClient {

	Role save(long roleId, String roleName);
	
	List<Role> findAll();
	ResponseEntity<Role> findById(long roleId);
	
	Role updateById(long roleId, String roleName);
	
	ResponseEntity<String> deleteById(long roleId);
}
