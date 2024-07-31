package com.cart.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ShoppingCartCheckoutResult {
    private boolean success;
    private String message;
    private List<CartItem> cartItems;
    private BigDecimal totalPrice;
    private LocalDateTime deliveryDate;

}
