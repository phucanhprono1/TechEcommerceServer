package com.example.techecommerceserver.service;



import com.example.techecommerceserver.dto.ProductDto;
import com.example.techecommerceserver.exception.ProductException;
import com.example.techecommerceserver.model.Product;

import java.util.List;

public interface ProductService {

	public List<Product> viewAllProduct() throws ProductException;

	public Product addProduct(Product product) throws ProductException;

	public Product updateProduct(Integer id, ProductDto productDto) throws ProductException;

	public Product viewProduct(Integer productId) throws ProductException;

	public List<Product> viewProductByCategory(Integer categoryId) throws ProductException;

	public Product removeProduct(Integer productId) throws ProductException;

}
