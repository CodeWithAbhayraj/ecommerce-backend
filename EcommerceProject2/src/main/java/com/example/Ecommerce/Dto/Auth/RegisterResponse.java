package com.example.Ecommerce.Dto.Auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterResponse {

    private String message;
    private String name;
    private String email;
}