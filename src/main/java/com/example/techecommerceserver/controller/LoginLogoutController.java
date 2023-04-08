package com.example.techecommerceserver.controller;


import com.example.techecommerceserver.exception.CustomerException;
import com.example.techecommerceserver.exception.LoginException;
import com.example.techecommerceserver.model.Customer;
import com.example.techecommerceserver.model.LoginDTO;
import com.example.techecommerceserver.model.RegisterDTO;
import com.example.techecommerceserver.service.CustomerService;
import com.example.techecommerceserver.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app")
public class LoginLogoutController {

	@Autowired
	private LoginService loginService;
	@Autowired
	private CustomerService cService;

	@PostMapping("/login")
	public ResponseEntity<String> logIn(@RequestBody LoginDTO loginDTO) throws LoginException {
		return new ResponseEntity<String>(loginService.loginAccount(loginDTO), HttpStatus.OK);
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logout(@RequestParam(required = false) String role,
			@RequestParam(required = false) String key) throws LoginException {
		return new ResponseEntity<String>(loginService.logoutAccount(role, key), HttpStatus.OK);
	}
	@PostMapping("/register")
	public ResponseEntity<Customer>register(@RequestBody Customer c) throws CustomerException {
		return new ResponseEntity<Customer>(cService.addCustomer(c), HttpStatus.OK);
	}

}
