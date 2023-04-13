package com.example.techecommerceserver.implementation;


import com.example.techecommerceserver.exception.CartException;
import com.example.techecommerceserver.exception.CustomerException;
import com.example.techecommerceserver.exception.ProductException;
import com.example.techecommerceserver.model.*;
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
		List<Product> itemList = cart.getProducts();
		int k = 0;
		boolean flag = true;
		for (int i = 0; i < itemList.size(); i++) {
			Product element = itemList.get(i);
			if (element.getProductId() == productId) {
				int a = cart.getProducts().get(i).getNumberSell();
				cart.getProducts().get(i).setNumberSell(a+1);
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
			itemOpt.get().setNumberSell(1);
			cart.setTotal_price(cart.getTotal_price()+itemOpt.get().getPrice());
			cart.getProducts().add(itemOpt.get());
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
		List<Product> itemList = cart.getProducts();
		boolean flag = false;
		for (int i = 0; i < itemList.size(); i++) {
			Product element = itemList.get(i);
			if (element.getProductId() == productId) {
				k = i;
				itemList.remove(element);
				flag = true;
				break;
			}
		}
		if (!flag) {
			throw new CartException("Product not removed from cart");
		}
		cart.setTotal_price(cart.getTotal_price()-itemOpt.get().getPrice());
		cart.setProducts(itemList);
		cRepo.save(cart);
		return cart;

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
		c.getProducts().clear();
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
		int k = 0;
		List<Product> itemList = cart.getProducts();
		boolean flag = true;
		for (int i = 0; i < itemList.size(); i++) {
			Product element = itemList.get(i);
			if (element.getProductId() == productId) {
				int a = cart.getProducts().get(i).getNumberSell();
				cart.getProducts().get(i).setNumberSell(a+1);
				k = i;
				cart.setTotal_price(cart.getTotal_price()+itemOpt.get().getPrice());
				flag = false;
			}
		}
		if (flag) {
			itemOpt.get().setNumberSell(1);
			cart.setTotal_price(cart.getTotal_price()+itemOpt.get().getPrice());
			cart.getProducts().add(itemOpt.get());
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
		List<Product> itemList = cart.getProducts();
		boolean flag = true;
		for (int i = 0; i < itemList.size(); i++) {
			Product element = itemList.get(i);
			if (element.getProductId() == productId) {
				int a = cart.getProducts().get(i).getNumberSell();
				cart.getProducts().get(i).setNumberSell(a-1);
				cart.setTotal_price(cart.getTotal_price()-itemOpt.get().getPrice());
				flag = false;
			}
		}
		/*if (flag) {
			itemOpt.get().setNumberSell(1);
			cart.setTotal_price(cart.getTotal_price()+itemOpt.get().getPrice());
			cart.getProducts().add(itemOpt.get());
		}*/
		cRepo.save(cart);
		return cart;
	}

}
