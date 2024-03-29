package com.example.techecommerceserver.implementation;


import com.example.techecommerceserver.exception.CartException;
import com.example.techecommerceserver.exception.CustomerException;
import com.example.techecommerceserver.exception.ProductException;
import com.example.techecommerceserver.model.Cart;
import com.example.techecommerceserver.model.CartItem;
import com.example.techecommerceserver.model.Customer;
import com.example.techecommerceserver.model.Product;
import com.example.techecommerceserver.repository.CartItemRepo;
import com.example.techecommerceserver.repository.CartRepo;
import com.example.techecommerceserver.repository.CustomerRepo;
import com.example.techecommerceserver.repository.ProductRepo;
import com.example.techecommerceserver.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class  CartServiceImpl implements CartService {

	@Autowired
	private CartRepo cRepo;

	@Autowired
	private CustomerRepo crRepo;

	@Autowired
	private ProductRepo pRepo;
	@Autowired
	private CartItemRepo cartItemRepo;

	@Override
	public Cart addProductToCart(Integer customerId, Integer productId,int quantity)
			throws CartException, CustomerException, ProductException {
		Optional<Customer> opt = crRepo.findById(customerId);
		if (opt.isEmpty())
			throw new CustomerException("Customer not found!");

		Optional<Product> itemOpt = pRepo.findById(productId);
		if (itemOpt.isEmpty())
			throw new ProductException("Product not found!");
		CartItem cartItem = new CartItem();
		cartItem.setProduct(itemOpt.get());
		cartItem.setQuantity(quantity);

		// Get the cart for the user, or create a new cart if one doesn't exist
		Cart cart = opt.get().getCart();
		List<CartItem> cartItems = cart.getCartItems();
		boolean flag = true;
		for(int i = 0;i<cartItems.size();i++) {
			if(cartItems.get(i).getProduct().getProductId() == productId) {
				cartItems.get(i).setQuantity(cartItems.get(i).getQuantity() + quantity);
				flag = false;
				break;
			}
		}
		if(flag) {
			CartItem cit = new CartItem();
			cit.setProduct(itemOpt.get());
			cit.setQuantity(quantity);

			cartItemRepo.save(cit);
			cartItems.add(cit);
		}
		if(cart.getProduct_quantity()==null||cart.getProduct_quantity()==0) {
			cart.setProduct_quantity(quantity);
		}
		else {
			cart.setProduct_quantity(cart.getProduct_quantity()+quantity);
		}
		cart.setCartItems(cartItems);
		float total = 0;

		for(CartItem c:cartItems) {
			total+=c.getProduct().getPrice()*c.getQuantity();
		}
		cart.setTotal_price(total);
		cRepo.save(cart);
		return cart;

	}

	@Override
	public Cart removeProductFromCart(Integer customerId, Integer productId)
			throws CartException, CustomerException, ProductException {
		Optional<Customer> opt = crRepo.findById(customerId);
		if (opt.isEmpty())
			throw new CustomerException("Customer not found!");

		Optional<Product> itemOpt = pRepo.findById(productId);
		if (itemOpt.isEmpty())
			throw new ProductException("Product not found!");
		Customer customer = opt.get();
		Cart cart = customer.getCart();
		List<CartItem> itemList = cart.getCartItems();
		boolean flag = false;
		for (int i = 0; i < itemList.size(); i++) {
			CartItem element = itemList.get(i);
			if (element.getProduct().getProductId() == productId) {
				cart.setProduct_quantity(cart.getProduct_quantity()-element.getQuantity());
				itemList.remove(element);
				flag = true;
				break;
			}
		}
		if (!flag) {
			throw new CartException("Product not removed from cart");
		}

		cart.setCartItems(itemList);
		float total = 0;
		for(CartItem c:itemList) {
			total+=c.getProduct().getPrice()*c.getQuantity();
		}
		cart.setTotal_price(total);
		cRepo.save(cart);
		return cart;

	}

	@Override
	public Cart removeAllProduct(Integer customerId) throws CartException, CustomerException {
		Optional<Customer> opt = crRepo.findById(customerId);
		if (opt.isEmpty())
			throw new CustomerException("Customer not found!");
		Cart c = opt.get().getCart();
		if (c == null) {
			throw new CartException("cart not found");
		}
		c.getCartItems().clear();
		c.setProduct_quantity(0);
		float total = 0;
		for(CartItem ca: c.getCartItems()) {
			total+=ca.getProduct().getPrice()*ca.getQuantity();
		}
		c.setTotal_price(total);
		return cRepo.save(c);

	}

	@Override
	public Cart increaseProductQuantity(Integer customerId, Integer productId)
			throws CartException, CustomerException, ProductException {
		Optional<Customer> opt = crRepo.findById(customerId);
		if (opt.isEmpty())
			throw new CustomerException("Customer not found!");

		Optional<Product> itemOpt = pRepo.findById(productId);
		if (itemOpt.isEmpty())
			throw new ProductException("Product not found!");

		Customer customer = opt.get();
		Cart cart = customer.getCart();
		List<CartItem> itemList = cart.getCartItems();
		boolean flag = true;
		for (int i = 0; i < itemList.size(); i++) {
			CartItem element = itemList.get(i);
			if (element.getProduct().getProductId() == productId) {
//				cart.setProduct_quantity(cart.getProduct_quantity() + 1);
				cart.getCartItems().get(i).setQuantity(cart.getCartItems().get(i).getQuantity() + 1);
				flag = false;
			}
		}
		if (flag) {
			throw new CartException("Product not found in cart");
		}
		float total = 0;
		int totalQuantity = 0;
		for(CartItem c:itemList) {
			total+=c.getProduct().getPrice()*c.getQuantity();
			totalQuantity+=c.getQuantity();
		}
		cart.setTotal_price(total);
		cart.setProduct_quantity(totalQuantity);
		cRepo.save(cart);
		return cart;
	}
	@Override
	public Cart decreaseProductQuantity(Integer customerId, Integer productId)
			throws CartException, CustomerException, ProductException {
		Optional<Customer> opt = crRepo.findById(customerId);
		if (opt.isEmpty())
			throw new CustomerException("Customer not found!");

		Optional<Product> itemOpt = pRepo.findById(productId);
		if (itemOpt.isEmpty())
			throw new ProductException("Product not found!");

		Customer customer = opt.get();
		Cart cart = customer.getCart();
		List<CartItem> itemList = cart.getCartItems();
		boolean flag = true;
		if (itemList.size() > 0) {
			for (int i = 0; i < itemList.size(); i++) {
				CartItem element = itemList.get(i);
				if (element.getProduct().getProductId() == productId) {
//					cart.setProduct_quantity(cart.getProduct_quantity() - 1);
					cart.getCartItems().get(i).setQuantity(element.getQuantity() - 1);
					if(cart.getCartItems().get(i).getQuantity() == 0) {
						itemList.remove(element);
					}
					flag = false;
				}
			}
		}

		if (flag) {
			throw new CartException("Product not found in cart");
		}
		float total = 0;
		int totalQuantity = 0;
		for(CartItem c:itemList) {
			total+=c.getProduct().getPrice()*c.getQuantity();
			totalQuantity+=c.getQuantity();
		}
		cart.setTotal_price(total);
		cart.setProduct_quantity(totalQuantity);
		cRepo.save(cart);
		return cart;
	}
}
