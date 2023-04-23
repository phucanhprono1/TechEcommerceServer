package com.example.techecommerceserver.repository;

import com.example.techecommerceserver.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    @Query("select count(*) from Product p where p.quantity < 10")
    long countEnd();
    @Query(nativeQuery = true, value = "SELECT * FROM product order by number_sell desc limit 5;")
    List<Product> topSell();
    @Query("SELECT p FROM Product p WHERE p.productName LIKE %:keyword% OR p.description like %:keyword% OR p.manufacturer like %:keyword%")
    List<Product> search(String keyword);
}
