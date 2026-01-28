package com.example.ecommerce.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Product {
    private @Id @GeneratedValue Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long stockQuantity;
    private String category;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Product() {
    }

    public Product(String name, String description, BigDecimal price, Long stockQuantity, String category, LocalDateTime createdAt) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category = category;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Long stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Product product)) return false;
        return Objects.equals(this.id, product.id) && Objects.equals(this.name, product.name)
                && Objects.equals(this.category, product.category) && Objects.equals(this.price, product.price)
                && Objects.equals(this.description, product.description) && Objects.equals(this.stockQuantity, product.stockQuantity)
                && Objects.equals(this.createdAt, product.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, stockQuantity, category, createdAt);
    }
}
