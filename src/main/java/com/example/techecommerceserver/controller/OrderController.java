package com.example.techecommerceserver.controller;


import com.example.techecommerceserver.exception.CartException;
import com.example.techecommerceserver.exception.CustomerException;
import com.example.techecommerceserver.exception.OrderException;
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

	@PostMapping("/add")
	public ResponseEntity<Orders> addOrder(@RequestParam("customerId") Integer customerId,
										   @RequestParam("locations") String locations,
										   @RequestParam("payment_method") String payment_method)
			throws OrderException, CustomerException, CartException {
		return new ResponseEntity<Orders>(oService.addOrder(customerId, locations, payment_method), HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<Orders> updateOrder(@RequestParam("id") int id,
											  @RequestParam("locations") String locations,
											  @RequestParam("payment_method") String payment_method) throws OrderException {
		return new ResponseEntity<Orders>(oService.updateOrder(id, locations, payment_method), HttpStatus.OK);
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

	@GetMapping("/view-us/{userId}")
	public ResponseEntity<List<Orders>> viewOrderByUserId(@PathVariable("userId") Integer userId)
			throws OrderException {
		return new ResponseEntity<List<Orders>>(oService.viewAllOrdersByUserId(userId), HttpStatus.OK);
	}

	@PostMapping("/confirm")
	public ResponseEntity<Orders> confirmOrder(@RequestParam("orderId") Integer orderId) {
		return new ResponseEntity<Orders>(oService.confirmOrder(orderId), HttpStatus.OK);
	}

	@PostMapping("/cancel")
	public ResponseEntity<Orders> cancelOrder(@RequestParam("orderId") Integer orderId) {
		return new ResponseEntity<Orders>(oService.cancelOrder(orderId), HttpStatus.OK);
	}
}
