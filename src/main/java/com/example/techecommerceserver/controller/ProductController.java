package com.example.techecommerceserver.controller;

import com.example.techecommerceserver.dto.ProductDto;
import com.example.techecommerceserver.exception.ProductException;
import com.example.techecommerceserver.model.Product;
import com.example.techecommerceserver.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Log4j2
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService pService;

	@GetMapping("/view")
	public ResponseEntity<List<Product>> viewAllProduct() throws ProductException {
		return new ResponseEntity<List<Product>>(pService.viewAllProduct(), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<Product> addProduct(@RequestBody Product p) throws ProductException {
		Product product = pService.addProduct(p);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public Product updateProduct(@PathVariable Integer id,@RequestBody ProductDto p) throws ProductException {
		return pService.updateProduct(id,p);
	}

	@GetMapping("/viewProduct/{productId}")
	public ResponseEntity<Product> viewProductById(@PathVariable Integer productId) throws ProductException {
		return new ResponseEntity<Product>(pService.viewProduct(productId), HttpStatus.OK);
	}

	@GetMapping("/view/{categoryId}")
	public ResponseEntity<List<Product>> viewProductByCategoryId(@PathVariable Integer categoryId)
			throws ProductException {
		return new ResponseEntity<List<Product>>(pService.viewProductByCategory(categoryId), HttpStatus.OK);
	}

	@DeleteMapping("/remove/{productId}")
	public ResponseEntity<Product> removeProductById(@PathVariable Integer productId)
			throws ProductException {
		return new ResponseEntity<Product>(pService.removeProduct(productId), HttpStatus.OK);
	}
	@GetMapping("/count")
	public long countNumberProduct() throws ProductException {
		return pService.countProduct();
	}
	@GetMapping("/countEnd")
	public long countEndProduct() throws ProductException {
		return pService.countEndProduct();
	}
	@GetMapping("/topSell")
	public ResponseEntity<List<Product>> getTopSell() throws IllegalArgumentException {
		return new ResponseEntity<List<Product>>(pService.viewTopSell(), HttpStatus.OK);
	}

}
