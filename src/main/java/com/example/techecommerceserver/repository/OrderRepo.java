package com.example.techecommerceserver.repository;

import com.example.techecommerceserver.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Orders, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM orders order by date desc limit 5;")
    List<Orders> viewNearlyOrder();

    @Query(" SELECT MONTH(e.date) AS month, e.total_price AS total_price FROM Orders e GROUP BY MONTH(e.date) ORDER BY e.date asc")
    List<Object[]> dataChart();
}
