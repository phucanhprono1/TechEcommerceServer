package com.example.techecommerceserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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
	private int quantity;
	private int numberSell;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "category_id")
	private Category category;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "product")
	private List<Comment> commentList;

	public Product(int productId) {
		this.productId = productId;
	}

	public Product() {

	}
}
