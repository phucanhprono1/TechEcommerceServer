package com.example.techecommerceserver.repository;

import com.example.techecommerceserver.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepo extends JpaRepository<CartItem, Integer> {
}
