package com.example.Ecommerce.Model;

import jakarta.persistence.Entity;

@Entity
public class Seller extends User {

    private String shopName;

    private String shopAddress;

    public Seller() {
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