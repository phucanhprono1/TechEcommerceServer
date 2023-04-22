package com.example.techecommerceserver.repository;

import com.example.techecommerceserver.model.Customer;
import com.example.techecommerceserver.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

	@Query("select c.orders from Customer c where c.cId=?1")
	public List<Orders> getAllOrderByCid(Integer cId);

	public Customer findByEmail(String email);
	public Customer findByUsername(String username);
//	public Customer findByUsernameAndPhone_number(String username, String phone_number);
}
