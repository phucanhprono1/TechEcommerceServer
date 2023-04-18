package com.example.techecommerceserver.controller;


import com.example.techecommerceserver.dto.CurrentCustomerDTO;
import com.example.techecommerceserver.dto.LoginFacebookDTO;
import com.example.techecommerceserver.dto.RegisterDTO;
import com.example.techecommerceserver.exception.CustomerException;
import com.example.techecommerceserver.exception.LoginException;
import com.example.techecommerceserver.exception.SessionLoginException;
import com.example.techecommerceserver.model.CurrentUserSession;
import com.example.techecommerceserver.model.Customer;
import com.example.techecommerceserver.dto.LoginDTO;
import com.example.techecommerceserver.repository.CustomerRepo;
import com.example.techecommerceserver.response.LoginResponse;
import com.example.techecommerceserver.service.CustomerService;
import com.example.techecommerceserver.service.LoginService;
import com.example.techecommerceserver.service.SessionLoginService;
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
	@Autowired
	private SessionLoginService sessionLoginService;

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
	public ResponseEntity<String> register(@RequestBody RegisterDTO c) throws CustomerException {
		Customer cs = customerRepo.findByUsername(c.getUsername());
		if (cs == null) {
			Customer reg = new Customer(c.getName(), c.getUsername(), c.getPhone_number(), c.getEmail(), c.getPassword());
			cService.addCustomer(reg);
			return new ResponseEntity<String>("Registered Successfully",HttpStatus.ACCEPTED);
		}
		else {
			return new ResponseEntity<String>("username existed",HttpStatus.NOT_ACCEPTABLE);
		}

	}
	@PostMapping("/loginfb")
	public ResponseEntity<LoginResponse> LoginFB(@RequestBody LoginFacebookDTO c) throws LoginException {

		return new ResponseEntity<LoginResponse>(loginService.loginFacebook(c),HttpStatus.OK);
	}
	@GetMapping("/currentUser")
	public ResponseEntity<CurrentCustomerDTO> getCurrentUser(@RequestParam(required = false) String key)throws SessionLoginException{
		return new ResponseEntity<CurrentCustomerDTO>(sessionLoginService.getCurrentCustomer(key),HttpStatus.OK);
	}
}
