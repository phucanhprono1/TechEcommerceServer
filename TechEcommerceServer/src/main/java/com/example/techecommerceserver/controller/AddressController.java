package com.example.techecommerceserver.controller;


import com.example.techecommerceserver.exception.CustomerException;
import com.example.techecommerceserver.exception.SessionLoginException;

import com.example.techecommerceserver.service.AddressService;
import com.example.techecommerceserver.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.techecommerceserver.exception.AddressException;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private AddressService addressService;

	@PutMapping("/update/{userId}")
	public ResponseEntity<Address> updateAddressByUserId(@PathVariable("userId") Integer userId,
			@RequestBody Address address, @RequestParam("key") String key)
			throws AddressException, CustomerException, SessionLoginException {
		return new ResponseEntity<Address>(addressService.updateAddressByUserId(address, userId, key), HttpStatus.OK);
	}

	@GetMapping("/view")
	public ResponseEntity<List<Address>> viewAllAddress(@RequestParam("key") String key)
			throws AddressException, SessionLoginException {
		return new ResponseEntity<List<Address>>(addressService.viewAllAddress(key), HttpStatus.OK);
	}

	@GetMapping("/view/{userId}")
	public ResponseEntity<Address> viewAddressByUserId(@RequestParam("key") String key,
													   @PathVariable("userId") Integer userId) throws CustomerException, SessionLoginException {
		return new ResponseEntity<Address>(addressService.viewAddressByUserId(userId, key), HttpStatus.OK);
	}
}
