package com.example.Ecommerce.Service;

public interface EmailService {

    void sendOtpEmail(String email, String otp);

    void sendRegistrationEmail(String email, String name);

}