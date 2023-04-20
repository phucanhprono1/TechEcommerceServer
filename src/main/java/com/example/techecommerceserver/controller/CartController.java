package com.example.techecommerceserver.controller;

import com.example.techecommerceserver.exception.CartException;
import com.example.techecommerceserver.exception.CustomerException;
import com.example.techecommerceserver.exception.ProductException;
import com.example.techecommerceserver.model.Cart;
import com.example.techecommerceserver.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cService;

	@GetMapping("/get-cart")
	public ResponseEntity<Cart> getCartById(@RequestParam("cartId") Integer id) {
		return new ResponseEntity<Cart>(cService.getCartById(id), HttpStatus.OK);

	}

	@PostMapping("/add")
	public ResponseEntity<Cart> addProductToCart(@RequestParam("customerId") Integer cId,
												 @RequestParam("productId") Integer productId) throws CartException, CustomerException, ProductException {
		return new ResponseEntity<Cart>(cService.addProductToCart(cId, productId), HttpStatus.OK);

	}

	/*@DeleteMapping("/remove")
	public ResponseEntity<Cart> removeProductFromCart(@RequestParam("customerId") Integer customerId,
			@RequestParam("productId") Integer productId) throws CartException, CustomerException, ProductException {
		return new ResponseEntity<Cart>(cService.removeProductFromCart(customerId, productId), HttpStatus.OK);
	}*/

	/*
	@DeleteMapping("/removeAll")
	public ResponseEntity<Cart> removeAllProduct(@RequestParam ("customerId") Integer customerId)
			throws CartException, CustomerException {
		return new ResponseEntity<Cart>(cService.removeAllProduct(customerId), HttpStatus.OK);
	}*/

	@PutMapping("/increase")
	public ResponseEntity<Cart> increaseProductQuantity(@RequestParam("customerId") Integer customerId,
			@RequestParam("productId") Integer productId) throws CartException, CustomerException, ProductException {
		return new ResponseEntity<Cart>(cService.increaseProductQuantity(customerId, productId), HttpStatus.OK);
	}

	@PutMapping("/decrease")
	public ResponseEntity<Cart> decreaseProductQuantity(@RequestParam("customerId") Integer customerId,
			@RequestParam("productId") Integer productId) throws CartException, CustomerException, ProductException {
		return new ResponseEntity<Cart>(cService.decreaseProductQuantity(customerId, productId), HttpStatus.OK);
	}
}
