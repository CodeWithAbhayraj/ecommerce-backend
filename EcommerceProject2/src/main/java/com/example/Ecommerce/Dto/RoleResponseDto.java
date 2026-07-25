package com.example.Ecommerce.Dto;

public class RoleResponseDto {

    private Long id;
    private String roleName;

    public RoleResponseDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}