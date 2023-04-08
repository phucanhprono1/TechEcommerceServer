package com.example.techecommerceserver.service;


import com.example.techecommerceserver.exception.CartException;
import com.example.techecommerceserver.exception.CustomerException;
import com.example.techecommerceserver.exception.OrderException;
import com.example.techecommerceserver.model.OrderDTO;
import com.example.techecommerceserver.model.Orders;

import java.util.List;

public interface OrderService {

	public Orders addOrder(Integer cid) throws OrderException, CustomerException, CartException;

	public OrderDTO updateOrder(Orders order) throws OrderException;

	public OrderDTO viewOrder(Integer orderId) throws OrderException;

	public List<Orders> viewAllOrder() throws OrderException;

	public List<Orders> viewAllOrdersByUserId(Integer userId) throws OrderException;

}