package com.example.techecommerceserver.repository;

import com.example.techecommerceserver.model.CartItem;
import com.example.techecommerceserver.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartItemRepo extends JpaRepository<CartItem, Integer> {
    @Query("SELECT c FROM CartItem c WHERE c.product.productId = :productId")
    Optional<CartItem> findByProductId(@Param("productId") int productId);
}
