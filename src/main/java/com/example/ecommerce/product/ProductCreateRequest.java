package com.example.ecommerce.product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class ProductCreateRequest {

    private String name;
    private String description;
    private BigDecimal price;
    private List<String> category;
    private Long stockQuantity;

    public ProductCreateRequest() {
    }

    public ProductCreateRequest(String name, String description, List<String> category, BigDecimal price, Long stockQuantity) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getCategory() {
        return this.category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getStockQuantity() {
        return this.stockQuantity;
    }

    public void setStockQuantity(Long stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ProductCreateRequest that)) return false;
        return Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(category, that.category) && Objects.equals(price, that.price) && Objects.equals(stockQuantity, that.stockQuantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, category, price, stockQuantity);
    }

    @Override
    public String toString() {
        return "ProductCreateRequest{" +
                "name='" + this.name + '\'' +
                ", description='" + this.description + '\'' +
                ", category=" + this.category +
                ", price=" + this.price +
                ", stockQuantity=" + this.stockQuantity +
                '}';
    }
}
