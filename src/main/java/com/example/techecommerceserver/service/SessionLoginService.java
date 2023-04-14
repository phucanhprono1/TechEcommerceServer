package com.example.techecommerceserver.service;


import com.example.techecommerceserver.dto.CurrentCustomerDTO;
import com.example.techecommerceserver.exception.SessionLoginException;
import com.example.techecommerceserver.model.Customer;

public interface SessionLoginService {

	public void checkAnyUserLoginStatus(String key) throws SessionLoginException;
	public CurrentCustomerDTO getCurrentCustomer(String key) throws SessionLoginException;

	public void checkAdminUserLoginStatus(String key) throws SessionLoginException;

	public void checkCustomerUserLoginStatus(String key) throws SessionLoginException;
}
