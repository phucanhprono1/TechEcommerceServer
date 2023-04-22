package com.example.techecommerceserver.implementation;

import com.example.techecommerceserver.exception.CartException;
import com.example.techecommerceserver.exception.CustomerException;
import com.example.techecommerceserver.exception.OrderException;
import com.example.techecommerceserver.model.*;
import com.example.techecommerceserver.repository.AddressRepo;
import com.example.techecommerceserver.repository.CartRepo;
import com.example.techecommerceserver.repository.CustomerRepo;
import com.example.techecommerceserver.repository.OrderRepo;
import com.example.techecommerceserver.service.CartService;
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
	private CartRepo cartRepo;
	@Autowired
	private CartService cartService;
	@Autowired
	private CustomerRepo customerRepo;


	@Autowired
	private AddressRepo addressRepo;

	@Override
	public Orders addOrder(Integer cid, String locations, String payment_method) throws OrderException, CustomerException, CartException {

		Optional<Customer> opt = customerRepo.findById(cid);
//		if (opt.isEmpty()) {
//			throw new CustomerException("Customer not found");
//		}
//
		Customer c = opt.get();
		Cart cart = c.getCart();
//		Orders o = new Orders();
//
//		o.setDate(LocalDateTime.now());
//		o.setOrderStatus("Pending");
//		o.setAddress(c.getAddress());
//		o.setCustomer(c);
//		if (cart.getCartItems().isEmpty()) {
//			throw new CartException("add minimum one product to order!");
//		} else {
//			o.getOrderItems().addAll(cart.getCartItems());
//			float k = 0;
//			for(Product m :cart.getProducts()){
//				k += m.getPrice();
//			}
//			o.setTotal_price(k);
//			return oRepo.save(o);
//		}
		Orders order = new Orders();
		order.setCustomer(c);
		order.setDate(LocalDateTime.now());
		order.setOrderStatus("Pending");
		order.setLocations(locations);
		order.setPayment_method(payment_method);
//
		float k = 0;
		List<OrderItem> orderItems = new ArrayList<>();
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setQuantity(cartItem.getQuantity());
			k+=cartItem.getProduct().getPrice()*cartItem.getQuantity();
			orderItem.setOrder(order);
			orderItems.add(orderItem);
		}
		order.setTotal_price(k);
		order.setOrderItems(orderItems);
		Orders savedOrder = oRepo.save(order);
		cartService.removeAllProduct(c.getCId());
		return savedOrder;
	}

	@Override
	public Orders updateOrder(int id, String locations,String payment_method) throws OrderException {
		Orders o = oRepo.findById(id).orElseThrow(() -> new OrderException("Order not found"));
		//Optional<Customer> opt = customerRepo.findById(id);
		o.setLocations(locations);
		o.setPayment_method(payment_method);
		return oRepo.save(o);
	}

	@Override
	public Orders viewOrder(Integer orderId) throws OrderException {
		Optional<Orders> o = oRepo.findById(orderId);
		if (o.isPresent()) {
			//OrderDTO a = new OrderDTO();
//			a.setOrderId(o.get().getOrderId());
//			a.setOrderStatus(o.get().getOrderStatus());
//			a.setDate(o.get().getDate());
//			a.setProductList(o.get().getProductList());
//			a.setCustomer(o.get().getCustomer());
//			a.setAddress(o.get().getAddress());
//			a.setPrice(o.get().getTotal_price());
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

	@Override
	public Orders confirmOrder(Integer orderId){
		Orders orders = oRepo.findById(orderId).get();
		orders.setOrderStatus("Xác nhận");
		return oRepo.save(orders);
	}

	@Override
	public Orders cancelOrder(Integer orderId){
		Orders orders = oRepo.findById(orderId).get();
		orders.setOrderStatus("Đã hủy");
		return oRepo.save(orders);
	}
}
