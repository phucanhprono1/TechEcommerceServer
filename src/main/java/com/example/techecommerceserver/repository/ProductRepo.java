package com.example.techecommerceserver.repository;

import com.example.techecommerceserver.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    @Query("select count(*) from Product p where p.quantity < 10")
    long countEnd();
    @Query(nativeQuery = true, value = "SELECT * FROM product order by number_sell desc limit 5;")
    List<Product> topSell();
    @Query("SELECT p FROM Product p WHERE "
            + "LOWER(p.productName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
            + "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
            + "LOWER(p.manufacturer) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
            + "LOWER(p.color) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
            + "LOWER(p.size) LIKE LOWER(CONCAT('%', :keyword, '%'))"
    )
    List<Product> search(@Param("keyword") String keyword);
    @Query("select p from Product p where p.quantity < 10 order by p.quantity asc")
    List<Product> topEndProduct();
}
