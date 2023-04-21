package com.example.techecommerceserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;

	private LocalDateTime date;
	private String orderStatus;

	@Column(name="total_price")
	private float total_price;


//	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private Customer customer;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderItem> orderItems = new ArrayList<>();

//	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
}
