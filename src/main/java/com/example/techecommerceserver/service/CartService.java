package com.example.techecommerceserver.service;


import com.example.techecommerceserver.exception.CartException;
import com.example.techecommerceserver.exception.CustomerException;
import com.example.techecommerceserver.exception.ProductException;
import com.example.techecommerceserver.model.Cart;

public interface CartService {

	public Cart addProductToCart(Integer customerId, Integer productId,int quantity)
			throws CartException, CustomerException, ProductException;

	public Cart removeProductFromCart(Integer customerId, Integer productId)
			throws CartException, CustomerException, ProductException;

	public Cart removeAllProduct(Integer customerId) throws CartException, CustomerException;

	public Cart increaseProductQuantity(Integer customerId, Integer productId)
			throws CartException, CustomerException, ProductException;

	public Cart decreaseProductQuantity(Integer customerId, Integer productId)
			throws CartException, CustomerException, ProductException;

}
