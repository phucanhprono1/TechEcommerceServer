package com.example.techecommerceserver.implementation;

import com.example.techecommerceserver.dto.OrderDTO;
import com.example.techecommerceserver.dto.OrderRequest;
import com.example.techecommerceserver.exception.CartException;
import com.example.techecommerceserver.exception.CustomerException;
import com.example.techecommerceserver.exception.OrderException;
import com.example.techecommerceserver.model.*;
import com.example.techecommerceserver.repository.*;
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
	@Autowired
	private OrderItemRepo orderItemRepo;
	@Autowired
	private ProductRepo productRepo;

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

		Orders order = new Orders();

		order.setCustomer(c);
		order.setDate(LocalDateTime.now());
		order.setOrderStatus("Pending");

		order.setLocation(orderRequest.getLocation());
//
		order.setPaymentMethod(orderRequest.getPaymentMethod());
		List<OrderItem> orderItems = new ArrayList<>();
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setQuantity(cartItem.getQuantity());
			Product p = cartItem.getProduct();
			p.setQuantity(p.getQuantity() - cartItem.getQuantity());
			p.setNumberSell(p.getNumberSell() + cartItem.getQuantity());
			productRepo.save(p);
//			orderItem.setOrder(order);
			orderItems.add(orderItem);
			orderItemRepo.save(orderItem);
		}
		order.setOrderItems(orderItems);
		order.setTotal_price(cart.getTotal_price());
		Orders savedOrder = oRepo.save(order);
		//xóa cart của customer sau khi order
		cartService.removeAllProduct(c.getCId());

		return savedOrder;
	}
	@Override
	public Orders updateOrder(int id, String locations,String payment_method) throws OrderException {
		Orders k = oRepo.findById(id).orElseThrow(() -> new OrderException("Order not found"));
		//Optional<Customer> opt = customerRepo.findById(id);
		/*java.util.Date now = new java.util.Date();
		Timestamp timestamp = new Timestamp(now.getTime());*/
		k.setDate(LocalDateTime.now());
		k.setLocation(locations);
		k.setPaymentMethod(payment_method);

		return oRepo.save(k);

		/*OrderDTO a = new OrderDTO();
		a.setOrderId(o.getOrderId());
		a.setOrderStatus(o.getOrderStatus());
		java.util.Date last = new Date(o.getDate().getTime());
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String strDate = dateFormat.format(last);
		a.setDate(strDate);
		a.setOrderItems(o.getOrderItems());
		a.setLocations(o.getLocations());
		a.setPayment_method(o.getPayment_method());
		a.setTotal_price(o.getTotal_price());
		a.setCustomer(o.getCustomer());
		return a;*/
	}
	@Override
	public Orders viewOrder(Integer orderId) throws OrderException {
		Optional<Orders> o = oRepo.findById(orderId);
		if (o.isPresent()) {
			/*OrderDTO a = new OrderDTO();
			a.setOrderId(o.get().getOrderId());
			a.setOrderStatus(o.get().getOrderStatus());
			java.util.Date last = new Date(o.get().getDate().getTime());
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			String strDate = dateFormat.format(last);
			a.setDate(strDate);
			a.setOrderItems(o.get().getOrderItems());
			a.setLocations(o.get().getLocations());
			a.setPayment_method(o.get().getPayment_method());
			a.setTotal_price(o.get().getTotal_price());
			a.setCustomer(o.get().getCustomer());
			return a;*/
			return o.get();
		} else {
			throw new OrderException("order not present!!");
		}
	}

	@Override
	public List<Orders> viewAllOrder() throws OrderException {
		List<Orders> orders = oRepo.findAll();
		/*List<OrderDTO> orderDTOS = new ArrayList<>();*/
		if (orders.size() > 0) {
			/*for(Orders o: orders){
				OrderDTO a = new OrderDTO();
				a.setOrderId(o.getOrderId());
				a.setOrderStatus(o.getOrderStatus());
				java.util.Date last = new Date(o.getDate().getTime());
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				String strDate = dateFormat.format(last);
				a.setDate(strDate);
				a.setOrderItems(o.getOrderItems());
				a.setLocations(o.getLocations());
				a.setPayment_method(o.getPayment_method());
				a.setTotal_price(o.getTotal_price());
				a.setCustomer(o.getCustomer());
				orderDTOS.add(a);
			}*/
			return orders;
		} else {
			throw new OrderException("Order not found");
		}
	}

	@Override
	public List<Orders> viewAllOrdersByUserId(Integer uderId) throws OrderException {
		List<Orders> orders = customerRepo.getAllOrderByCid(uderId);
		/*List<OrderDTO> orderDTOS = new ArrayList<>();*/
		if (orders.size() > 0) {
			/*for(Orders o: orders){
				OrderDTO a = new OrderDTO();
				a.setOrderId(o.getOrderId());
				a.setOrderStatus(o.getOrderStatus());
				java.util.Date last = new Date(o.getDate().getTime());
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				String strDate = dateFormat.format(last);
				a.setDate(strDate);
				a.setOrderItems(o.getOrderItems());
				a.setLocations(o.getLocations());
				a.setPayment_method(o.getPayment_method());
				a.setTotal_price(o.getTotal_price());
				a.setCustomer(o.getCustomer());
				orderDTOS.add(a);
			}*/
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
		/*OrderDTO a = new OrderDTO();
		a.setOrderId(o.getOrderId());
		a.setOrderStatus(o.getOrderStatus());
		java.util.Date last = new Date(o.getDate().getTime());
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String strDate = dateFormat.format(last);
		a.setDate(strDate);
		a.setOrderItems(o.getOrderItems());
		a.setLocations(o.getLocations());
		a.setPayment_method(o.getPayment_method());
		a.setTotal_price(o.getTotal_price());
		a.setCustomer(o.getCustomer());
		return a;*/
	}

	@Override
	public Orders cancelOrder(Integer orderId){
		Orders orders = oRepo.findById(orderId).get();
		orders.setOrderStatus("Đã hủy");
		return oRepo.save(orders);

		/*OrderDTO a = new OrderDTO();
		a.setOrderId(o.getOrderId());
		a.setOrderStatus(o.getOrderStatus());
		java.util.Date last = new Date(o.getDate().getTime());
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String strDate = dateFormat.format(last);
		a.setDate(strDate);
		a.setOrderItems(o.getOrderItems());
		a.setLocations(o.getLocations());
		a.setPayment_method(o.getPayment_method());
		a.setTotal_price(o.getTotal_price());
		a.setCustomer(o.getCustomer());
		return a;*/
	}

	@Override
	public long countOrder() throws OrderException {
		return oRepo.count();
	}

}
