package com.example.Ecommerce.Model;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseEntity {

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name = "verified")
    private Boolean verified;

    @Column(name = "active")
    private boolean active = false;

//    @Column(name = "role")
//    private String roleName;

    // trim() and email lowercase fun

//    @PrePersist
//    @PreUpdate
//    private void normalizeData() {
//
//        if (name != null) {
//            name = name.trim().replaceAll("\\s+", " ");
//        }
//
//        if (email != null) {
//            email = email.trim().toLowerCase();
//        }
//    }


    public User() {
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    //    public String getRoleName() {
//        return roleName;
//    }
//
//    public void setRoleName(String roleName) {
//        this.roleName = roleName;
//    }
}