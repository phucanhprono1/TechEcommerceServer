package com.example.techecommerceserver.dto;

import com.example.techecommerceserver.model.Address;
import lombok.Data;

@Data

public class OrderRequest {
    private Address address;
    private String paymentMethod;
    private String location;
}
