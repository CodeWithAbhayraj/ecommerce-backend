package com.example.Ecommerce.ServiceImpl;

import com.example.Ecommerce.Dto.Auth.*;
import com.example.Ecommerce.Model.OtpVerification;
import com.example.Ecommerce.Model.User;
import com.example.Ecommerce.Repository.OtpRepository;
import com.example.Ecommerce.Repository.UserRepository;
import com.example.Ecommerce.Service.AuthService;
import com.example.Ecommerce.Service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final OtpRepository otpRepository;
    private final EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ===========================
    // Register User
    // ===========================
    @Override
    @Transactional
    public RegisterResponse register(RegisterRequest request) {

        String cleanName = request.getName()
                .trim()
                .replaceAll("\\s+", " ");

        String email = request.getEmail()
                .trim()
                .toLowerCase();

        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();

        user.setName(cleanName);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setVerified(false);
        user.setActive(false);

        // Save User
        userRepository.save(user);

        // Generate OTP
        String otp = String.valueOf(new Random().nextInt(900000) + 100000);

        // Save OTP
        OtpVerification otpVerification = new OtpVerification();
        otpVerification.setEmail(email);
        otpVerification.setOtp(otp);
        otpVerification.setVerified(false);
        otpVerification.setExpiryTime(LocalDateTime.now().plusMinutes(5));

        otpRepository.save(otpVerification);

        // Send OTP Email
        emailService.sendOtpEmail(email, otp);

        return new RegisterResponse(
                "Registration Successful. OTP Sent Successfully.",
                user.getName(),
                user.getEmail()
        );
    }

    // ===========================
    // Login User
    // ===========================
    @Override
    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {

        String email = request.getEmail()
                .trim()
                .toLowerCase();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid Email"));

        if (!user.isVerified()) {
            throw new RuntimeException("Please verify your email first.");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }

        return new LoginResponse(
                "Login Successful",
                user.getName(),
                user.getEmail()
        );
    }

    // ===========================
    // Verify OTP
    // ===========================
    @Override
    @Transactional
    public String verifyOtp(VerifyOtpRequest request) {

        String email = request.getEmail()
                .trim()
                .toLowerCase();

        OtpVerification otp = otpRepository
                .findByEmailAndOtp(email, request.getOtp())
                .orElseThrow(() -> new RuntimeException("Invalid OTP"));

        if (otp.getExpiryTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("OTP Expired");
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        user.setVerified(true);
        user.setActive(true);

        userRepository.save(user);

        otpRepository.delete(otp);

        return "Email Verified Successfully";
    }

    // ===========================
    // Resend OTP
    // ===========================
    @Override
    @Transactional
    public String resendOtp(String email) {

        email = email.trim().toLowerCase();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String otp = String.valueOf((int) (100000 + Math.random() * 900000));

        OtpVerification otpVerification = otpRepository
                .findByEmail(email)
                .orElse(new OtpVerification());

        otpVerification.setEmail(email);
        otpVerification.setOtp(otp);
        otpVerification.setVerified(false);
        otpVerification.setExpiryTime(LocalDateTime.now().plusMinutes(5));

        otpRepository.save(otpVerification);

        emailService.sendOtpEmail(email, otp);

        return "OTP Resent Successfully";
    }
}