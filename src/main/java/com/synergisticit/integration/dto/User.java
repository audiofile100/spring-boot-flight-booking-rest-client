package com.synergisticit.integration.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
public class User {

	private Long userId;
	private String username;
	private String password;
	private String email;
	
	@ToString.Exclude
	private List<Role> roles = new ArrayList<>();
}
