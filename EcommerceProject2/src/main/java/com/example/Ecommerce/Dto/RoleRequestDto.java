package com.example.Ecommerce.Dto;

import jakarta.validation.constraints.NotBlank;

public class RoleRequestDto {

    @NotBlank(message = "Role name is required")
    private String roleName;

    public RoleRequestDto() {
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}