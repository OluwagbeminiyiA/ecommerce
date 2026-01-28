package com.example.ecommerce.product;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Long id) {
        super("There is no Product with product id: " + id);
    }
}
