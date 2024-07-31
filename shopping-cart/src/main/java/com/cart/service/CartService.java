package com.cart.service;

import com.cart.entity.ShoppingCart;
import org.springframework.stereotype.Service;

@Service
public interface CartService {

    ShoppingCart createShoppingCart(int userId);

    ShoppingCart getUserShoppingCart(int userId);

    ShoppingCart addBookToCart(int userId, int bookId,int quantity);
}
