package com.example.techecommerceserver.implementation;

import com.example.techecommerceserver.exception.CustomerException;
import com.example.techecommerceserver.model.Cart;
import com.example.techecommerceserver.model.Customer;
import com.example.techecommerceserver.repository.CartRepo;
import com.example.techecommerceserver.repository.CustomerRepo;
import com.example.techecommerceserver.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepo cRepo;

	@Autowired
	CartRepo cartRepo;

	@Override
	public Customer addCustomer(Customer customer) throws CustomerException {
		Cart cart = new Cart();
		customer.setCart(cart);
		cart.setCustomer(customer);
		Customer c = cRepo.save(customer);

		if (c != null) {
			return c;
		} else {
			throw new CustomerException("customer not added");
		}
	}

	@Override
	public Customer updateCustomer(Integer id, Customer customer) throws CustomerException {
		Customer c = cRepo.findById(customer.getCId()).orElseThrow(() -> new CustomerException("Customer not found"));
		c.setName(customer.getName());
		c.setEmail(customer.getEmail());
		c.setUsername(customer.getUsername());
		c.setPhone_number(customer.getPhone_number());
		if (c != null) {
			cRepo.save(c);
		}
		return c;
	}

	@Override
	public Customer remove(Integer customerId) throws CustomerException {
		Optional<Customer> opt = cRepo.findById(customerId);
		if (opt.isPresent()) {
			Customer c = opt.get();
			cRepo.delete(c);
			return c;
		} else {
			throw new CustomerException("Customer not found with cid - " + customerId);
		}

	}

	@Override
	public List<Customer> viewAllCustomer() throws CustomerException {
		List<Customer> customers = cRepo.findAll();
		if (customers.size() > 0) {
			return customers;
		} else {
			throw new CustomerException("customer not found");
		}
	}

	@Override
	public Customer viewCustomer(Integer cId) throws CustomerException {
		Customer opt = cRepo.findById(cId).orElseThrow(() -> new CustomerException("For id " + cId));
		return opt;

	}

	@Override
	public Customer findCustomerByUsername(String customerUsername) throws CustomerException {
		return null;
	}

	@Override
	public long countCustomer() throws CustomerException {
		return cRepo.count();
	}
}
