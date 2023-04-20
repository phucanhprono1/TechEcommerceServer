package com.example.techecommerceserver.implementation;


import com.example.techecommerceserver.exception.CartException;
import com.example.techecommerceserver.exception.CustomerException;
import com.example.techecommerceserver.exception.ProductException;
import com.example.techecommerceserver.model.*;
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
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepo cRepo;

	@Autowired
	private CustomerRepo crRepo;

	@Autowired
	private ProductRepo pRepo;

	@Autowired
	private CartItemRepo cartItemRepo;

	@Override
	public Cart getCartById(Integer cart_id) {
		Cart cart = cRepo.findById(cart_id).get();
		return cart;
	}

	@Override
	public Cart addProductToCart(Integer customerId, Integer productId)
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
		CartItem l = new CartItem();
		l.setProduct(itemOpt.get());
		l.setCustomer(opt.get());
		int k = 0;
		boolean flag = true;
		for (int i = 0; i < itemList.size(); i++) {
			CartItem element = itemList.get(i);
			if (element.getProduct().getProductId() == productId) {
				int a = cart.getCartItems().get(i).getNumberSell();
				cart.getCartItems().get(i).setNumberSell(a+1);
				CartItem p = cartItemRepo.findById(cart.getCartItems().get(i).getId()).get();
				cartItemRepo.save(p);
				k = i;
				cart.setTotal_price(cart.getTotal_price()+itemOpt.get().getPrice());
				/*if (cart.getProduct_quantity() == null) {
					cart.setProduct_quantity(1);
					cart.setTotal_price(cart.getTotal_price()+element.getPrice());
				} else {
					cart.setProduct_quantity(cart.getProduct_quantity() + 1);
					cart.setTotal_price(cart.getTotal_price()+element.getPrice());
				}*/
				flag = false;
			}
		}
		if (flag) {
			l.setNumberSell(1);
			cart.setTotal_price(cart.getTotal_price()+itemOpt.get().getPrice());
			cart.getCartItems().add(l);
			cartItemRepo.save(l);
		}
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
		int k = 0;
		List<CartItem> itemList = cart.getCartItems();
		boolean flag = false;
		for (int i = 0; i < itemList.size(); i++) {
			CartItem element = itemList.get(i);
			if (element.getProduct().getProductId() == productId) {
				k = i;
				int num = element.getNumberSell();
				cart.setTotal_price(cart.getTotal_price()-itemOpt.get().getPrice()*num);
				itemList.remove(element);
				cart.setCartItems(itemList);
				cRepo.save(cart);
				List<CartItem> m = cartItemRepo.findAll();
				for(CartItem b: m){
					if(b.getProduct().getProductId() == productId){
						cartItemRepo.deleteById(b.getId());
					}
				}
				flag = true;
				break;
			}
		}
		if (!flag) {
			throw new CartException("Product not removed from cart");
		}
		return cRepo.save(cart);
	}

	@Override
	public Cart removeAllProduct(Integer customerId) throws CartException, CustomerException {
		return null;
	}

/*	@Override
	public Cart removeAllProduct(Integer customerId) throws CartException, CustomerException {
		Optional<Customer> opt = crRepo.findById(customerId);
		if (opt.isEmpty())
			throw new CustomerException("Customer not found!");
		Cart c = opt.get().getCart();
		if (c == null) {
			throw new CartException("cart not found");
		}
		c.getCartItems().clear();
		List<CartItem> list = cartItemRepo.findAll();
		for(CartItem m: list){
			if(m.getCustomer().getCId() == customerId){
				cartItemRepo.deleteById(m.getId());
			}
		}
		return cRepo.save(c);
	}*/

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
		CartItem l = new CartItem();
		l.setProduct(itemOpt.get());
		l.setCustomer(opt.get());
		int k = 0;
		boolean flag = true;
		for (int i = 0; i < itemList.size(); i++) {
			CartItem element = itemList.get(i);
			if (element.getProduct().getProductId() == productId) {
				int a = cart.getCartItems().get(i).getNumberSell();
				cart.getCartItems().get(i).setNumberSell(a+1);
				CartItem p = cartItemRepo.findById(cart.getCartItems().get(i).getId()).get();
				cartItemRepo.save(p);
				k = i;
				cart.setTotal_price(cart.getTotal_price()+itemOpt.get().getPrice());
				/*if (cart.getProduct_quantity() == null) {
					cart.setProduct_quantity(1);
					cart.setTotal_price(cart.getTotal_price()+element.getPrice());
				} else {
					cart.setProduct_quantity(cart.getProduct_quantity() + 1);
					cart.setTotal_price(cart.getTotal_price()+element.getPrice());
				}*/
				flag = false;
			}
		}
		if (flag) {
			l.setNumberSell(1);
			cart.setTotal_price(cart.getTotal_price()+itemOpt.get().getPrice());
			cart.getCartItems().add(l);
			cartItemRepo.save(l);
		}

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
		CartItem l = new CartItem();
		l.setProduct(itemOpt.get());
		l.setCustomer(opt.get());
		int k = 0;
		boolean flag = true;
		for (int i = 0; i < itemList.size(); i++) {
			CartItem element = itemList.get(i);
			if (element.getProduct().getProductId() == productId) {
				int a = cart.getCartItems().get(i).getNumberSell();
				cart.getCartItems().get(i).setNumberSell(a-1);
				CartItem p = cartItemRepo.findById(cart.getCartItems().get(i).getId()).get();
				if(a-1 > 0){
					cartItemRepo.save(p);
					k = i;
					cart.setTotal_price(cart.getTotal_price()-itemOpt.get().getPrice());
				}
				flag = false;
			}
		}
		cRepo.save(cart);
		return cart;
	}
}
