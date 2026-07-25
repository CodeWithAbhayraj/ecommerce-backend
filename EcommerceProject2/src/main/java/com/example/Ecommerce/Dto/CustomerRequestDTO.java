package com.example.Ecommerce.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CustomerRequestDTO {

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50)
    @Pattern(
            regexp = "^[A-Za-z ]+$",
            message = "Name can contain only alphabets and spaces"
    )
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Email")
    private String email;


    @NotBlank(message = "Password is required")
    @Size(min = 6)
    private String password;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Mobile Number is required")
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Invalid Mobile Number"
    )
    private String mobile;

    public CustomerRequestDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email=email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password=password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address=address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile=mobile;
    }
}