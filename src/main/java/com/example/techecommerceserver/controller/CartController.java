package com.example.techecommerceserver.controller;

import com.example.techecommerceserver.exception.CartException;
import com.example.techecommerceserver.exception.CustomerException;
import com.example.techecommerceserver.exception.ProductException;
import com.example.techecommerceserver.model.Cart;
import com.example.techecommerceserver.repository.CartRepo;
import com.example.techecommerceserver.repository.CustomerRepo;
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
	@Autowired
	private CartRepo cartRepo;
	@Autowired
	private CustomerRepo customerRepo;
	@PostMapping("/add/{customerId}/{productId}/{quantity}")
	public ResponseEntity<Cart> addProductToCart(@PathVariable(value = "customerId") Integer customerId,
												 @PathVariable(value = "productId") Integer productId,@PathVariable(value = "quantity")int quantity) throws CartException, CustomerException, ProductException {
		return new ResponseEntity<Cart>(cService.addProductToCart(customerId, productId,quantity), HttpStatus.OK);

	}

	@GetMapping("/view/{customerId}")
	public ResponseEntity<Cart> getCartByCustomerId(@PathVariable(value = "customerId") Integer customerId)
			throws CartException, CustomerException {
		return new ResponseEntity<Cart>(customerRepo.findById(customerId).get().getCart(), HttpStatus.OK);
	}
	@DeleteMapping("/remove/{cartId}/{productId}")
	public ResponseEntity<Cart> removeProductFromCart(@PathVariable("cartId") Integer cartId,
			@PathVariable("productId") Integer productId) throws CartException, CustomerException, ProductException {
		return new ResponseEntity<Cart>(cService.removeProductFromCart(cartId, productId), HttpStatus.OK);
	}

	@DeleteMapping("/remove/{customerId}")
	public ResponseEntity<Cart> removeAllProduct(@PathVariable("customerId") Integer customerId)
			throws CartException, CustomerException {
		return new ResponseEntity<Cart>(cService.removeAllProduct(customerId), HttpStatus.OK);
	}

	@PutMapping("/increase/{customerId}/{itemCartId}")
	public ResponseEntity<Cart> increaseProductQuantity(@PathVariable("customerId") Integer customerId,
			@PathVariable("itemCartId") Integer itemCartId) throws CartException, CustomerException, ProductException {
		return new ResponseEntity<Cart>(cService.increaseProductQuantity(customerId, itemCartId), HttpStatus.OK);
	}

	@PutMapping("/decrease/{customerId}/{itemCartId}")
	public ResponseEntity<Cart> decreaseProductQuantity(@PathVariable("cartId") Integer customerId,
			@PathVariable("itemCartId") Integer itemCartId) throws CartException, CustomerException, ProductException {
		return new ResponseEntity<Cart>(cService.decreaseProductQuantity(customerId, itemCartId), HttpStatus.OK);
	}
}
