package com.example.techecommerceserver.service;

import com.os.exception.CustomerException;
import com.os.model.Customer;

import java.util.List;

public interface CustomerService {

	public Customer addCustomer(Customer customer) throws CustomerException;

	public Customer updateCustomer(Customer customer) throws CustomerException;

	public Customer remove(Integer customerId) throws CustomerException;

	public List<Customer> viewAllCustomer() throws CustomerException;

}
