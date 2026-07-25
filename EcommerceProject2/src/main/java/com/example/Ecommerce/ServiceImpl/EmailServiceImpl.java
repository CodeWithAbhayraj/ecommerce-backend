package com.example.Ecommerce.ServiceImpl;

import com.example.Ecommerce.Service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;


    
    @Override
    public void sendOtpEmail(String email, String otp) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);
        message.setSubject("OTP Verification");

        message.setText(
                "Your OTP is: " + otp +
                        "\n\nThis OTP is valid for 5 minutes."
        );

        mailSender.send(message);
    }



    @Override
    public void sendRegistrationEmail(String toEmail, String name) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(toEmail);
        message.setSubject("Welcome to Ecommerce Application");

        message.setText(
                "Hello " + name + ",\n\n" +
                        "Your account has been created successfully.\n\n" +
                        "Thank you for registering with us.\n\n" +
                        "Regards,\n" +
                        "Ecommerce Team"
        );

        mailSender.send(message);
    }
}