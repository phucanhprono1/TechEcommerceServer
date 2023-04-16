package com.example.techecommerceserver.service;

import com.example.techecommerceserver.model.CartItem;
import com.example.techecommerceserver.model.OrderDetail;

import java.util.ArrayList;
import java.util.Optional;

public interface OrderDetailService {
    ArrayList<OrderDetail> getAll();
    Optional<OrderDetail> getById(int id);
    OrderDetail save(OrderDetail OrderDetail);
    void delete(int id);
}
