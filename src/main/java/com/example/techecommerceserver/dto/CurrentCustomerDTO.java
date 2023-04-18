package com.example.techecommerceserver.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CurrentCustomerDTO {
    @JsonIgnore
    private int id;
    private String name;

    private String username;

    private String phone_number;

    private String email;

}
