package com.example.techecommerceserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cId;
	private String name;
	@Column(unique = true, name = "username")
	private String username;
	@Column(unique = true, name = "phone_number")
	private String phone_number;
	@Column(unique = false, name = "email")
	private String email;
	private String password;

	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	private Cart cart;

	public Customer(String name, String username, String phone_number, String email, String password) {
		this.name = name;
		this.username = username;
		this.phone_number = phone_number;
		this.email = email;
		this.password = password;
	}


	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
	private List<Orders> orders;

}
