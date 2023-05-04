package com.example.techecommerceserver.controller;


import com.example.techecommerceserver.dto.OrderRequest;
import com.example.techecommerceserver.exception.CartException;
import com.example.techecommerceserver.exception.CustomerException;
import com.example.techecommerceserver.exception.OrderException;
import com.example.techecommerceserver.dto.OrderDTO;
import com.example.techecommerceserver.exception.ProductException;
import com.example.techecommerceserver.model.Orders;
import com.example.techecommerceserver.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Log4j2
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService oService;

	@PostMapping("/add/{customerId}")
	public ResponseEntity<Orders> addOrder(@PathVariable("customerId") Integer customerId, @RequestBody OrderRequest orderRequest)
			throws OrderException, CustomerException, CartException {
		return new ResponseEntity<Orders>(oService.addOrder(customerId,orderRequest), HttpStatus.CREATED);
	}

	@PutMapping("/update/{orderId}/{locations}/{payment_method}/{orderStatus}")
	public ResponseEntity<Orders> updateOrder(@PathVariable("orderId") int orderId,
											  @PathVariable("locations") String locations,
											  @PathVariable("payment_method") String payment_method,
											  @PathVariable("orderStatus") String orderStatus) throws OrderException {
		return new ResponseEntity<Orders>(oService.updateOrder(orderId, locations, payment_method, orderStatus), HttpStatus.OK);
	}

	@GetMapping("/view/{id}")
	public ResponseEntity<Orders> viewOrderById(@PathVariable("id") Integer orderId) throws OrderException {
		return new ResponseEntity<Orders>(oService.viewOrder(orderId), HttpStatus.OK);
	}

	@GetMapping("/view")
	public ResponseEntity<List<Orders>> viewAllOrder() throws OrderException {
		return new ResponseEntity<List<Orders>>(oService.viewAllOrder(), HttpStatus.OK);
	}

	/*@GetMapping("/view/{userId}")
	public ResponseEntity<List<Orders>> viewOrderByUserId(@PathVariable("userId") Integer userId)
			throws OrderException {
		return new ResponseEntity<List<Orders>>(oService.viewAllOrdersByUserId(userId), HttpStatus.OK);
	}*/

	@GetMapping("/viewOrderByUser/{userId}")
	public ResponseEntity<List<Orders>> viewOrderByUserId(@PathVariable("userId") Integer userId)
			throws OrderException {
		return new ResponseEntity<List<Orders>>(oService.viewAllOrdersByUserId(userId), HttpStatus.OK);
	}

	@PostMapping("/confirm/{orderId}")
	public ResponseEntity<Orders> confirmOrder(@PathVariable("orderId") Integer orderId) {
		return new ResponseEntity<Orders>(oService.confirmOrder(orderId), HttpStatus.OK);
	}

	@PostMapping("/cancel/{orderId}")
	public ResponseEntity<Orders> cancelOrder(@PathVariable("orderId") Integer orderId) {
		return new ResponseEntity<Orders>(oService.cancelOrder(orderId), HttpStatus.OK);
	}

}
