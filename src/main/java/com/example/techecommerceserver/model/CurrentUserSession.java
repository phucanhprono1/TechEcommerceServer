package com.example.techecommerceserver.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
public class CurrentUserSession {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer currSessionId;

	@NotNull(message = "username is require")

	private String username;

	@NotNull(message = "Date time is require")
	private LocalDateTime loginDateTime;

	@NotNull(message = "Role is require")
	private String role;

	private String privateKey;

}
