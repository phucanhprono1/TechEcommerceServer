package com.example.techecommerceserver.controller;


import com.example.techecommerceserver.exception.CustomerException;
import com.example.techecommerceserver.exception.LoginException;
import com.example.techecommerceserver.model.Customer;
import com.example.techecommerceserver.dto.LoginDTO;
import com.example.techecommerceserver.repository.CustomerRepo;
import com.example.techecommerceserver.response.LoginResponse;
import com.example.techecommerceserver.service.CustomerService;
import com.example.techecommerceserver.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginLogoutController {

	@Autowired
	private LoginService loginService;
	@Autowired
	private CustomerService cService;
	@Autowired
	private CustomerRepo customerRepo;

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> logIn(@RequestBody LoginDTO loginDTO) throws LoginException {
		return new ResponseEntity<LoginResponse>(loginService.loginAccount(loginDTO), HttpStatus.OK);
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logout(@RequestParam(required = false) String role,
			@RequestParam(required = false) String key) throws LoginException {
		return new ResponseEntity<String>(loginService.logoutAccount(role, key), HttpStatus.OK);
	}
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody Customer c) throws CustomerException {
		Customer cs = customerRepo.findByUsername(c.getUsername());
		if (cs == null) {

			cService.addCustomer(c);
			return new ResponseEntity<String>("Registered Successfully",HttpStatus.ACCEPTED);
		}
		else {
			return new ResponseEntity<String>("username existed",HttpStatus.NOT_ACCEPTABLE);
		}

	}
}
