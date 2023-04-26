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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;

	private LocalDateTime date;
	private String orderStatus;
	private String location;
	@Column(name="total_price")
	private float total_price;

	private String paymentMethod;


	@ManyToOne(cascade = CascadeType.ALL)
	private Customer customer;

	@Embedded
	@ElementCollection
	private List<OrderItem> orderItems = new ArrayList<>();

}
