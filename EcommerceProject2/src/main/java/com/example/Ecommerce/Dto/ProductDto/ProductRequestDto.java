package com.example.Ecommerce.Dto.ProductDto;

public class ProductRequestDto {

    private String productname;
    private String brand;
    private String product_discription;
    private Double price;
    private String category;
    private Integer stock;
    private String imageurl;



    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getProduct_discription() {
        return product_discription;
    }

    public void setProduct_discription(String product_discription) {
        this.product_discription = product_discription;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
