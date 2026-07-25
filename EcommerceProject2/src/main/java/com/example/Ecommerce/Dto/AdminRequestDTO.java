package com.example.Ecommerce.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AdminRequestDTO {

//    @NotBlank(message = "Name is required")
//    @Size(min = 3, max = 30, message = "Name should be between 3 and 30 characters")
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50)
    @Pattern(
            regexp = "^[A-Za-z ]+$",
            message = "Name can contain only alphabets and spaces"
    )
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Email Format")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Admin Code is required")
    @Pattern(regexp = "^ADM\\d{3}$",
            message = "Admin Code should be like ADM001")
    private String adminCode;

    public AdminRequestDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdminCode() {
        return adminCode;
    }

    public void setAdminCode(String adminCode) {
        this.adminCode = adminCode;
    }
}