package com.example.ecommerce.category;

import com.example.ecommerce.product.Product;
import com.example.ecommerce.product.ProductCategory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Category {
    private @Id
    @GeneratedValue Long id;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<ProductCategory> products = new ArrayList<>();

    @Column(unique = true)
    private String name;

    public Category() {}

    public Category(String name) {
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProductCategory> getProducts() {
        return this.products;
    }

    public void setProducts(List<ProductCategory> products) {
        this.products = products;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
