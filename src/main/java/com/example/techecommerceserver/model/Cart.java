package com.example.techecommerceserver.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cart_id")
	private Integer cartId;
	private Integer product_quantity;
	private float total_price;
	@OneToMany( cascade = CascadeType.ALL)
	private List<CartItem> cartItems = new ArrayList<>();


	@OneToOne(cascade = CascadeType.ALL)
	private Customer customer;
}
