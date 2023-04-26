package com.example.techecommerceserver.implementation;

import com.example.techecommerceserver.dto.OrderDTO;
import com.example.techecommerceserver.dto.OrderRequest;
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
	public Orders addOrder(Integer cid, OrderRequest orderRequest) throws OrderException, CustomerException, CartException {

		Optional<Customer> opt = customerRepo.findById(cid);
//		if (opt.isEmpty()) {
//			throw new CustomerException("Customer not found");
//		}
//
		Customer c = opt.get();
		c.setAddress(orderRequest.getAddress());
		addressRepo.save(c.getAddress());
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
		order.setAddress(c.getAddress());
		order.setLocation(orderRequest.getLocation());
//

		List<OrderItem> orderItems = new ArrayList<>();
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setQuantity(cartItem.getQuantity());

			orderItem.setOrder(order);
			orderItems.add(orderItem);
		}
		order.setOrderItems(orderItems);
		order.setTotal_price(cart.getTotal_price());
		Orders savedOrder = oRepo.save(order);
		//xóa cart của customer sau khi order
		cartService.removeAllProduct(c.getCId());

		return savedOrder;
	}

	@Override
	public OrderDTO updateOrder(Orders order) throws OrderException {
//		Orders o = oRepo.findById(order.getOrderId()).orElseThrow(() -> new OrderException("Order not found"));
//		Optional<Customer> opt = customerRepo.findById(order.getOrderId());
//		Customer c = opt.get();
//		order.setCustomer(c);
//		Cart cart = c.getCart();
//		o.setProductList(new ArrayList<>(cart.getProducts()));
//		float k = 0;
//		for(Product m :cart.getProducts()){
//			k += m.getPrice();
//		}
//		o.setTotal_price(k);
		OrderDTO a = new OrderDTO();
//		if (o != null) {
//			oRepo.save(order);
//			a.setOrderId(order.getOrderId());
//			a.setOrderStatus(order.getOrderStatus());
//			a.setDate(order.getDate());
//			a.setProductList(order.getProductList());
//			a.setCustomer(order.getCustomer());
//			a.setAddress(order.getAddress());
//			a.setPrice(order.getTotal_price());
//		}
		return a;
	}

	@Override
	public OrderDTO viewOrder(Integer orderId) throws OrderException {
		Optional<Orders> o = oRepo.findById(orderId);
		if (o.isPresent()) {
			OrderDTO a = new OrderDTO();
//			a.setOrderId(o.get().getOrderId());
//			a.setOrderStatus(o.get().getOrderStatus());
//			a.setDate(o.get().getDate());
//			a.setProductList(o.get().getProductList());
//			a.setCustomer(o.get().getCustomer());
//			a.setAddress(o.get().getAddress());
//			a.setPrice(o.get().getTotal_price());
			return a;
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
	public long countOrder() throws OrderException {
		return oRepo.count();
	}

}
