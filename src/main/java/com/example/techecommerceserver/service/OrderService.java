package com.example.techecommerceserver.service;


import com.example.techecommerceserver.dto.OrderRequest;
import com.example.techecommerceserver.exception.CartException;
import com.example.techecommerceserver.exception.CustomerException;
import com.example.techecommerceserver.exception.OrderException;
import com.example.techecommerceserver.dto.OrderDTO;
import com.example.techecommerceserver.model.Orders;

import java.util.List;

public interface OrderService {

	public Orders addOrder(Integer cid, OrderRequest orderRequest) throws OrderException, CustomerException, CartException;

	public Orders updateOrder(int id, String locations,String payment_method, String orderStatus) throws OrderException;

	public Orders viewOrder(Integer orderId) throws OrderException;

	public List<Orders> viewAllOrder() throws OrderException;

	public List<Orders> viewAllOrdersByUserId(Integer userId) throws OrderException;

	public Orders confirmOrder(Integer orderId);

	public Orders cancelOrder(Integer orderId);

	long countOrder() throws OrderException;
}
