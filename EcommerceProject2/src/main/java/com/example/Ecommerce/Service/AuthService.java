package com.example.Ecommerce.Service;

import com.example.Ecommerce.Dto.Auth.LoginRequest;
import com.example.Ecommerce.Dto.Auth.LoginResponse;
import com.example.Ecommerce.Dto.Auth.RegisterRequest;
import com.example.Ecommerce.Dto.Auth.RegisterResponse;
import com.example.Ecommerce.Dto.Auth.VerifyOtpRequest;

public interface AuthService {

    RegisterResponse register(RegisterRequest request);

    String verifyOtp(VerifyOtpRequest request);

    String resendOtp(String email);

    LoginResponse login(LoginRequest request);
}