package com.example.ecommerce.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, Long> {

    List<CartItems> findAllByCartId(Long cartId);
    void deleteAllByCartId(Long cartId);
}
