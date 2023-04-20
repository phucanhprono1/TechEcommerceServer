package com.example.techecommerceserver.controller;


import com.example.techecommerceserver.exception.AdminException;
import com.example.techecommerceserver.service.AdminService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@Log4j2
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@PostMapping("/add")
	public ResponseEntity<String> createAdmin() throws AdminException {
		return new ResponseEntity<String>(adminService.createNewAdmin(), HttpStatus.CREATED);
	}

}
