package com.example.Ecommerce.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Role extends BaseEntity {

//    @Column(unique = true, nullable = false)
//    private String roleName;

    private String roleName;

    public Role() {
    }


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}