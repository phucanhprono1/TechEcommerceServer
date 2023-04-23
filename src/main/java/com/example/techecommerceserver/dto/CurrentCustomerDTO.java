package com.example.techecommerceserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CurrentCustomerDTO {
    private int id;
    private String name;

    private String username;

    private String phone_number;

    private String email;

}
