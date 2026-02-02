package com.example.ecommerce.product;

import com.example.ecommerce.category.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class ProductCategory {

    private @Id
    @GeneratedValue Long id;

    @ManyToOne(optional = false)
    @JsonIgnore
    private Product product;

    @ManyToOne(optional = false)
    private Category category;

    public ProductCategory() {
    }

    public ProductCategory(Product product, Category category) {
        this.product = product;
        this.category = category;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ProductCategory that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(product, that.product) && Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, category);
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "id=" + this.id +
                ", product=" + this.product +
                ", category=" + this.category +
                '}';
    }
}
