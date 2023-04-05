package com.example.techecommerceserver.model;

import lombok.Data;

@Data
public class LoginDTO {

	private String email;
	private String password;
	private String role;

}
