package com.example.Ecommerce.Controller;

import com.example.Ecommerce.Dto.Auth.LoginRequest;
import com.example.Ecommerce.Dto.Auth.LoginResponse;
import com.example.Ecommerce.Dto.Auth.RegisterRequest;
import com.example.Ecommerce.Dto.Auth.RegisterResponse;
import com.example.Ecommerce.Dto.Auth.VerifyOtpRequest;
import com.example.Ecommerce.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // ============================
    // Register API
    // ============================
    @PostMapping("/register")
    public RegisterResponse register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    // ============================
    // Verify OTP API
    // ============================
    @PostMapping("/verify-otp")
    public String verifyOtp(@Valid @RequestBody VerifyOtpRequest request) {
        return authService.verifyOtp(request);
    }

    // ============================
    // Resend OTP API
    // ============================
    @PostMapping("/resend-otp")
    public String resendOtp(@RequestParam String email) {
        return authService.resendOtp(email);
    }

    // ============================
    // Login API
    // ============================
    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }
}