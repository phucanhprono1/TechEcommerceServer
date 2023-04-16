package com.example.techecommerceserver.controller;


import com.example.techecommerceserver.exception.CartException;
import com.example.techecommerceserver.exception.CustomerException;
import com.example.techecommerceserver.exception.OrderException;
import com.example.techecommerceserver.model.OrderDTO;
import com.example.techecommerceserver.model.Orders;
import com.example.techecommerceserver.service.OrderService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService oService;

	@PostMapping("/add")
	public ResponseEntity<Orders> addOrder(@RequestParam("customerId") Integer customerId,
										   @RequestParam("address") String address,
										   @RequestParam("payment_method") String payment_method)
			throws OrderException, CustomerException, CartException {
		return new ResponseEntity<Orders>(oService.addOrder(customerId, address, payment_method), HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<Orders> updateOrder(@RequestParam("id") int id,
												@RequestParam("address") String address,
												@RequestParam("payment_method") String payment_method) throws OrderException {
		return new ResponseEntity<Orders>(oService.updateOrder(id, address, payment_method), HttpStatus.OK);
	}

	@GetMapping("/view/{id}")
	public ResponseEntity<Orders> viewOrderById(@PathVariable("id") Integer orderId) throws OrderException {
		return new ResponseEntity<Orders>(oService.viewOrder(orderId), HttpStatus.OK);
	}

	@GetMapping("/view")
	public ResponseEntity<List<Orders>> viewAllOrder() throws OrderException {
		return new ResponseEntity<List<Orders>>(oService.viewAllOrder(), HttpStatus.OK);
	}

	@GetMapping("/view-us/{userId}")
	public ResponseEntity<List<Orders>> viewOrderByUserId(@PathVariable("userId") Integer userId)
			throws OrderException {
		return new ResponseEntity<List<Orders>>(oService.viewAllOrdersByUserId(userId), HttpStatus.OK);
	}
}
