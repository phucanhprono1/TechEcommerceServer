package com.example.techecommerceserver.model;

import lombok.Data;

import jakarta.persistence.*;

@Data
@Embeddable
@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer productId;
	private String productName;
	private Double price;
	private String color;
	private String dimension;
	private String specification;
	private String menufacturer;

	@ManyToOne(cascade = CascadeType.ALL)
	private Category category;
}
