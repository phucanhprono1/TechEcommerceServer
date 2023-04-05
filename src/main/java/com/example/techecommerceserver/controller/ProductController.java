package com.example.techecommerceserver.controller;

import com.os.exception.ProductException;
import com.os.model.Product;
import com.os.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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

	@PutMapping("/update")
	public ResponseEntity<Product> updateProduct(@RequestBody Product p) throws ProductException {
		Product product = pService.updateProduct(p);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@GetMapping("/view/{productId}")
	public ResponseEntity<Product> viewProductById(@PathVariable("productId") Integer productId) throws ProductException {
		return new ResponseEntity<Product>(pService.viewProduct(productId), HttpStatus.OK);
	}

	@GetMapping("/view/{categoryId}")
	public ResponseEntity<List<Product>> viewProductByCategoryId(@PathVariable("categoryId") Integer categoryId)
			throws ProductException {
		return new ResponseEntity<List<Product>>(pService.viewProductByCategory(categoryId), HttpStatus.OK);
	}

	@DeleteMapping("/remove/{productId}")
	public ResponseEntity<Product> removeProductById(@PathVariable("productId") Integer productId)
			throws ProductException {
		return new ResponseEntity<Product>(pService.removeProduct(productId), HttpStatus.OK);
	}

}
