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
	public ResponseEntity<Orders> addOrder(@RequestParam("customerId") Integer customerId)
			throws OrderException, CustomerException, CartException {
		return new ResponseEntity<Orders>(oService.addOrder(customerId), HttpStatus.CREATED);
	}

	/*@PutMapping("/update")
	public ResponseEntity<OrderDTO> updateOrder(@RequestBody Orders order) throws OrderException {
		return new ResponseEntity<OrderDTO>(oService.updateOrder(order), HttpStatus.OK);
	}*/

	@GetMapping("/view/{id}")
	public ResponseEntity<OrderDTO> viewOrderById(@PathVariable("id") Integer orderId) throws OrderException {
		return new ResponseEntity<OrderDTO>(oService.viewOrder(orderId), HttpStatus.OK);
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
}
