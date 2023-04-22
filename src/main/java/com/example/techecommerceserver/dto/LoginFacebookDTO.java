package com.example.techecommerceserver.dto;

import lombok.Data;

@Data
public class LoginFacebookDTO {

    public String name;
    public String email;
    public String phone_number;
    public String username;
    public String password;


    public String role;
}
