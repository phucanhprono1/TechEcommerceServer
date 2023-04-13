package com.example.techecommerceserver.implementation;

import com.example.techecommerceserver.model.CartItem;
import com.example.techecommerceserver.repository.CartItemRepo;
import com.example.techecommerceserver.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CartItemImpl implements CartItemService {

    @Autowired
    private CartItemRepo cartItemRepo;

    @Override
    public ArrayList<CartItem> getAll() {
        ArrayList<CartItem> list = new ArrayList<>();
        Iterable it = cartItemRepo.findAll();
        for(Object CartItem: it){
            list.add((CartItem) CartItem);
        }
        return list;
    }

    @Override
    public Optional<CartItem> getById(int id) {
        return cartItemRepo.findById(id);
    }

    @Override
    public CartItem save(CartItem cartItem) {
        return cartItemRepo.save(cartItem);
    }


    @Override
    public void delete(int id) {
        cartItemRepo.deleteById(id);
    }
}
