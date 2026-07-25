package com.example.Ecommerce.Dto;



public class SellerResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String shopName;
    private String shopAddress;

    private String roleName;



    public SellerResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
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

//    public String getShopName() {
//        return shopName;
//    }

    public void setShopName(String shopName) {
        this.shopName=shopName;
    }

//    public String getShopAddress() {
//        return shopAddress;
//    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress=shopAddress;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}