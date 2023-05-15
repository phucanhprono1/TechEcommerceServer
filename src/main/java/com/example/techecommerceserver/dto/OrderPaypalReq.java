package com.example.techecommerceserver.dto;

import com.example.techecommerceserver.model.Address;
import lombok.Data;

@Data
public class OrderPaypalReq {
    private Address address;
    private String paymentstatus;
    private String location;
}
