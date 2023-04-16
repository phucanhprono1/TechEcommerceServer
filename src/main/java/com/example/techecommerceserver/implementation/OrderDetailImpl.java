package com.example.techecommerceserver.implementation;

import com.example.techecommerceserver.model.OrderDetail;
import com.example.techecommerceserver.repository.OrderDetailRepo;
import com.example.techecommerceserver.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class OrderDetailImpl implements OrderDetailService {

    @Autowired
    private com.example.techecommerceserver.repository.OrderDetailRepo OrderDetailRepo;

    @Override
    public ArrayList<OrderDetail> getAll() {
        ArrayList<OrderDetail> list = new ArrayList<>();
        Iterable it = OrderDetailRepo.findAll();
        for(Object OrderDetail: it){
            list.add((OrderDetail) OrderDetail);
        }
        return list;
    }

    @Override
    public Optional<OrderDetail> getById(int id) {
        return OrderDetailRepo.findById(id);
    }

    @Override
    public OrderDetail save(OrderDetail OrderDetail) {
        return OrderDetailRepo.save(OrderDetail);
    }


    @Override
    public void delete(int id) {
        OrderDetailRepo.deleteById(id);
    }
}
