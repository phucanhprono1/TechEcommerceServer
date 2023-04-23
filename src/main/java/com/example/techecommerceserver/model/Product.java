package com.example.techecommerceserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@Embeddable
@Entity
@AllArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer productId;
	private String productName;
	private float price;
	private String color;
	private String description;
	private String image;
	private String size;
	private String manufacturer;
	private int availability;
	private int numberSell;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "category_id")
	private Category category;


	public Product() {

	}
}
