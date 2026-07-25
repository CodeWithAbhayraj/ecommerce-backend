package com.example.Ecommerce.Model;

import jakarta.persistence.Entity;

@Entity
public class Admin extends User {

    private String adminCode;

    public Admin() {
    }

    public String getAdminCode() {
        return adminCode;
    }

    public void setAdminCode(String adminCode) {
        this.adminCode=adminCode;
    }

//    public void setRoles(String roleName) {
//    }
}