package com.example.Ecommerce.Model;

import jakarta.persistence.Entity;

@Entity
public class Customer extends User {

    private String address;

    private String mobile;

    public Customer() {
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