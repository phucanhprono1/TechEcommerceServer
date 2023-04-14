package com.example.techecommerceserver.dto;

import lombok.Data;

@Data
public class LoginFacebookDTO {
    public String name;

    public String username;
    public String phone_number;
    public String password;

    public String email;
    public String role;
}
