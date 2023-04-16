package com.example.techecommerceserver.implementation;

import com.example.techecommerceserver.exception.CartException;
import com.example.techecommerceserver.exception.CustomerException;
import com.example.techecommerceserver.exception.OrderException;
import com.example.techecommerceserver.model.*;
import com.example.techecommerceserver.repository.AddressRepo;
import com.example.techecommerceserver.repository.CustomerRepo;
import com.example.techecommerceserver.repository.OrderDetailRepo;
import com.example.techecommerceserver.repository.OrderRepo;
import com.example.techecommerceserver.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepo oRepo;

	@Autowired
	private OrderDetailRepo orderDetailRepo;

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private AddressRepo addressRepo;

	@Override
	public Orders addOrder(Integer cid, String address, String payment_method) throws OrderException, CustomerException, CartException {
		Optional<Customer> opt = customerRepo.findById(cid);
		if (opt.isEmpty()) {
			throw new CustomerException("Customer not found");
		}

		Customer c = opt.get();
		Cart cart = c.getCart();
		Orders o = new Orders();
		o.setPayment_method(payment_method);
		o.setDate(LocalDateTime.now());
		o.setOrderStatus("Pending");
		o.setAddress(address);
		o.setCustomer(c);
		if (cart.getCartItems().isEmpty()) {
			throw new CartException("add minimum one product to order!");
		} else {
			List<CartItem> b = new ArrayList<>(cart.getCartItems());
			List<OrderDetail> res = new ArrayList<>();
			for(CartItem p: b){
				OrderDetail m = new OrderDetail();
				m.setCustomer(p.getCustomer());
				m.setProduct(p.getProduct());
				m.setNumberSell(p.getNumberSell());
				res.add(m);
				orderDetailRepo.save(m);
			}
			o.setOrderDetails(new ArrayList<>(res));
			o.setTotal_price(cart.getTotal_price());
			return oRepo.save(o);
/*			List<OrderDetail> temp = orderDetailRepo.findAll();
			List<OrderDetail> res1 = new ArrayList<>();
			for (OrderDetail as: temp){
				if(as.getCustomer().getCId() == cid && as.getOrder_id() == 0){
					as.setOrder_id(orders.getOrderId());
					res1.add(as);
					orderDetailRepo.save(as);
				}
			}
			orders.setOrderDetails(res1);
			return oRepo.save(orders);*/
		}
	}

	@Override
	public Orders updateOrder(int id, String address,String payment_method) throws OrderException {
		Orders o = oRepo.findById(id).orElseThrow(() -> new OrderException("Order not found"));
		Optional<Customer> opt = customerRepo.findById(id);
		o.setAddress(address);
		o.setPayment_method(payment_method);
		OrderDTO a = new OrderDTO();
		return oRepo.save(o);
	}

	@Override
	public Orders viewOrder(Integer orderId) throws OrderException {
		Optional<Orders> o = oRepo.findById(orderId);
		if (o.isPresent()) {
/*			OrderDTO a = new OrderDTO();
			a.setOrderId(o.get().getOrderId());
			a.setOrderStatus(o.get().getOrderStatus());
			a.setDate(o.get().getDate());
			a.setCartItems(o.get().getCartItems());
			a.setCustomer(o.get().getCustomer());
			a.setAddress(o.get().getAddress());
			a.setPayment_method(o.get().getPayment_method());
			a.setPrice(o.get().getTotal_price());*/
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
