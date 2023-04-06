package com.example.techecommerceserver.implementation;

import com.example.techecommerceserver.exception.CartException;
import com.example.techecommerceserver.exception.CustomerException;
import com.example.techecommerceserver.exception.OrderException;
import com.example.techecommerceserver.model.Cart;
import com.example.techecommerceserver.model.Customer;
import com.example.techecommerceserver.model.Orders;
import com.example.techecommerceserver.repository.CustomerRepo;
import com.example.techecommerceserver.repository.OrderRepo;
import com.example.techecommerceserver.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepo oRepo;

	@Autowired
	private CustomerRepo customerRepo;

	@Override
	public Orders addOrder(Integer cid) throws OrderException, CustomerException, CartException {

		Optional<Customer> opt = customerRepo.findById(cid);
		if (opt.isEmpty()) {
			throw new CustomerException("Customer not found");
		}

		Customer c = opt.get();
		Cart cart = c.getCart();
		Orders o = new Orders();

		o.setDate(LocalDateTime.now());
		o.setOrderStatus("Pending");
		o.setAddress(c.getAddress());
		o.setCustomer(c);
		if (cart.getProducts().isEmpty()) {
			throw new CartException("add minimum one product to order!");
		} else {
			o.setProductList(new ArrayList<>(cart.getProducts()));
			return oRepo.save(o);
		}

	}

	@Override
	public Orders updateOrder(Orders order) throws OrderException {
		Orders o = oRepo.findById(order.getOrderId()).orElseThrow(() -> new OrderException("Order not found"));
		if (o != null) {
			oRepo.save(order);
		}
		return o;
	}

	@Override
	public Orders viewOrder(Integer orderId) throws OrderException {
		Optional<Orders> o = oRepo.findById(orderId);
		if (o.isPresent()) {
			return o.get();
		} else {
			throw new OrderException("order not present!!");
		}

	}

	@Override
	public List<Orders> viewAllOrder() throws OrderException {
		List<Orders> orders = oRepo.findAll();
		if (orders.size() > 0) {
			return orders;
		} else {
			throw new OrderException("Order not found");
		}
	}

	@Override
	public List<Orders> viewAllOrdersByUserId(Integer uderId) throws OrderException {
		List<Orders> orders = customerRepo.getAllOrderByCid(uderId);
		if (orders.size() > 0) {
			return orders;
		} else {
			throw new OrderException("Order not found");
		}
	}

}
