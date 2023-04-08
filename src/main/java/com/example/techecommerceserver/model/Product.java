package com.example.techecommerceserver.model;

import lombok.Data;

import javax.persistence.*;

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
	private String link;


	@ManyToOne(cascade = CascadeType.ALL)
	private Category category;
}
