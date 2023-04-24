package com.example.techecommerceserver.controller;

import com.example.techecommerceserver.exception.CustomerException;
import com.example.techecommerceserver.exception.ProductException;
import com.example.techecommerceserver.model.Customer;
import com.example.techecommerceserver.service.CustomerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Log4j2
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService cService;

	@PostMapping("/add")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer c) throws CustomerException {
		return new ResponseEntity<Customer>(cService.addCustomer(c), HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public Customer updateCustomer(@PathVariable Integer id, @RequestBody Customer c)
			throws CustomerException {
//		return new ResponseEntity<Customer>(cService.updateCustomer(c), HttpStatus.OK);
		return cService.updateCustomer(id,c);
	}


	@DeleteMapping("/remove/{id}")
	public ResponseEntity<Customer> removeCustomerById(@PathVariable("id") Integer customerId) throws CustomerException {
		return new ResponseEntity<Customer>(cService.remove(customerId), HttpStatus.OK);
	}

	@GetMapping("/view")
	public ResponseEntity<List<Customer>> viewAllCustomer() throws CustomerException {
		return new ResponseEntity<List<Customer>>(cService.viewAllCustomer(), HttpStatus.OK);
	}
	@GetMapping("/viewCustomer/{cId}")
	public ResponseEntity<Customer> viewCustomerById(@PathVariable Integer cId) throws CustomerException {
		return new ResponseEntity<Customer>(cService.viewCustomer(cId), HttpStatus.OK);
	}
	@GetMapping("/count")
	public long countNumberCustomer() throws CustomerException {
		return cService.countCustomer();
	}

}
