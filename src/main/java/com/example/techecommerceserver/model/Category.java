package com.example.techecommerceserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Category {

	@Id
//	@GeneratedValue(strategy = GenerationType.)
	private Integer categoryId;
	private String categoryName;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "category")
	private List<Product> productList;

	public Category(int categoryId) {
		this.categoryId = categoryId;
	}

	public Category() {
	}

}
