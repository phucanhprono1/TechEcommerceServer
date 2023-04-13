package com.example.techecommerceserver.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cartId;
	/*private Integer product_quantity;*/
	private double total_price;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<CartItem> cartItems;

	@OneToOne(cascade = CascadeType.ALL)
	private Customer customer;
}
