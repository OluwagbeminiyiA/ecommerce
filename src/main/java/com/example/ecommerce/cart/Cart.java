package com.example.ecommerce.cart;

import com.example.ecommerce.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Validated
@Table(name = "cart")
public class Cart {

    private @Id @GeneratedValue Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CartItems> cartItems = new ArrayList<>();


    @Column(name = "total", nullable = false)
    @Min(value = 0, message = "Total must be greater than or equal to 0")
    private BigDecimal total;

    @Column(name = "discount")
    @Min(value = 0, message = "Discount cannot be negative")
    private BigDecimal discount;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "updatedAt")
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @PrePersist
    @PreUpdate
    private void onPersistorUpdate() {
        calculateTotalAndDiscount();
        this.lastModifiedDate = LocalDateTime.now();
    }

    public Cart(User user, List<CartItems> cartItems, BigDecimal total, BigDecimal discount, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        this.user = user;
        this.cartItems = cartItems;
        this.total = total;
        this.discount = discount;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public void setCartItems(List<CartItems> cartItems) {
        if (this.cartItems != null) {
            this.cartItems.clear();
        }
        this.cartItems = cartItems;
        if (cartItems != null) {
            this.cartItems.addAll(cartItems);
        }

        calculateTotalAndDiscount();
    }

    void calculateTotalAndDiscount() {
        setTotal(BigDecimal.ZERO);
        setDiscount(BigDecimal.ZERO);

        if ((this.cartItems != null) && (!this.cartItems.isEmpty())) {
            for (CartItems cartItem : this.cartItems) {
                BigDecimal total = cartItem.getProduct().getPrice().multiply(new BigDecimal(cartItem.getQuantity()));
                setTotal(getTotal().add(total));
                BigDecimal itemDiscount = total
                        .multiply(cartItem.getProduct().getDiscount())
                        .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

                setDiscount(getDiscount().add(itemDiscount));            }
        }

    }
    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", user=" + user.getId() +
                ", cartItems=" + cartItems +
                ", total=" + total +
                ", discount=" + discount +
                ", createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}
