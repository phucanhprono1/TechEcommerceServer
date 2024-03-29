package com.example.techecommerceserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class Login {

	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;


	@NotNull(message = "username format is require")
	private String username;

	@NotNull(message = "Password is require")
	@Size(min = 6, max = 16, message = "Password should between 6 to 16")
	private String password;

	@NotNull(message = "Role is require")
	private String role;

}
