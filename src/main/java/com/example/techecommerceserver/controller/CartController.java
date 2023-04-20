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
@RequestMapping("/customer/cart")
public class CartController {

	@Autowired
	private CartService cService;

	@PostMapping("/add/{customerId}/{productId}/{quantity}")
	public ResponseEntity<Cart> addProductToCart(@PathVariable(value = "customerId") Integer customerId,
												 @PathVariable(value = "productId") Integer productId,@PathVariable(value = "quantity")int quantity) throws CartException, CustomerException, ProductException {
		return new ResponseEntity<Cart>(cService.addProductToCart(customerId, productId,quantity), HttpStatus.OK);

	}

	@DeleteMapping("/remove/{cartId}/{productId}")
	public ResponseEntity<Cart> removeProductFromCart(@PathVariable("cartId") Integer cartId,
			@PathVariable("productId") Integer productId) throws CartException, CustomerException, ProductException {
		return new ResponseEntity<Cart>(cService.removeProductFromCart(cartId, productId), HttpStatus.OK);
	}

	@DeleteMapping("/remove/{cartId}")
	public ResponseEntity<Cart> removeAllProduct(@PathVariable("cartId") Integer cartId)
			throws CartException, CustomerException {
		return new ResponseEntity<Cart>(cService.removeAllProduct(cartId), HttpStatus.OK);
	}

	@PutMapping("/increase/{cartId}/{productId}")
	public ResponseEntity<Cart> increaseProductQuantity(@PathVariable("cartId") Integer cartId,
			@PathVariable("productId") Integer productId) throws CartException, CustomerException, ProductException {
		return new ResponseEntity<Cart>(cService.increaseProductQuantity(cartId, productId), HttpStatus.OK);
	}

	@PutMapping("/decrease/{cartId}/{productId}")
	public ResponseEntity<Cart> decreaseProductQuantity(@PathVariable("cartId") Integer cartId,
			@PathVariable("productId") Integer productId) throws CartException, CustomerException, ProductException {
		return new ResponseEntity<Cart>(cService.decreaseProductQuantity(cartId, productId), HttpStatus.OK);
	}
}
