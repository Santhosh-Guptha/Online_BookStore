package com.cart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "cart_id")
    @JsonIgnore
    private ShoppingCart cart;
    private long productId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookId", referencedColumnName = "bookId")
    @JsonIgnore
    private Book book;
    private int quantity;
    private BigDecimal price;
}
