package com.cart.service;

import com.cart.entity.CartItem;
import com.cart.entity.ShoppingCart;
import com.cart.entity.ShoppingCartCheckoutResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {

    ShoppingCart createShoppingCart(int userId);

    ShoppingCart getUserShoppingCart(int userId);

    List<CartItem> getCartItems(int userId);

    ShoppingCart addItemsToCart(int userId, long bookId, int quantity);

    String removeItemFromCart(int userId ,int itemId);

    ShoppingCartCheckoutResult checkout(int userId);
}
