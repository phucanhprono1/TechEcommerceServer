package com.example.techecommerceserver.model;

import lombok.Data;

import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
public class CurrentUserSession {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer currSessionId;

	@NotNull(message = "Email is required")
	@Email
	private String email;

	@NotNull(message = "Date time is required")
	private LocalDateTime loginDateTime;

	@NotNull(message = "Role is required")
	private String role;

	private String privateKey;

}
