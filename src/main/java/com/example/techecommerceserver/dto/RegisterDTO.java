package com.example.techecommerceserver.dto;

import lombok.Data;

@Data
public class RegisterDTO {

    private String name;
    private String email;
    private String phone_number;
    private String username;

    private String password;
}
