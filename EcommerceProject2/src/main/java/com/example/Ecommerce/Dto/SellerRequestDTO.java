package com.example.Ecommerce.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class SellerRequestDTO {

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


    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&]).+$",
            message = "Password must contain at least one uppercase, one lowercase, one digit and one special character"
    )
    private String password;



    @NotBlank(message = "Shop Name is required")
    private String shopName;


    @NotBlank(message = "Shop Address is required")
    private String shopAddress;


    public SellerRequestDTO() {
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

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName=shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress=shopAddress;
    }
}