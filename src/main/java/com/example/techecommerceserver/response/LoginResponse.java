package com.example.techecommerceserver.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    String response;

    String username;
    String role;
    String key;
}
