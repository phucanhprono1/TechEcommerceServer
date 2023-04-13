package com.example.techecommerceserver.service;

import com.example.techecommerceserver.model.CartItem;

import java.util.ArrayList;
import java.util.Optional;

public interface CartItemService {
    ArrayList<CartItem> getAll();
    Optional<CartItem> getById(int id);
    CartItem save(CartItem cartItem);
    void delete(int id);
}
