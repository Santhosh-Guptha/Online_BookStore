package com.cart.controller;

import com.cart.entity.CartItem;
import com.cart.entity.ShoppingCart;
import com.cart.entity.ShoppingCartCheckoutResult;
import com.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/{userId}")
    public ResponseEntity<ShoppingCart> createShoppingCart(@PathVariable int userId){
        return new ResponseEntity<>(cartService.createShoppingCart(userId),HttpStatus.CREATED);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<ShoppingCart> getShoppingCart(@PathVariable int userId){
        return new ResponseEntity<>(cartService.getUserShoppingCart(userId), HttpStatus.OK);
    }

    @GetMapping("/items/{userId}")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable int userId){
        return new ResponseEntity<>(cartService.getCartItems(userId),HttpStatus.OK);
    }

    @PostMapping("/items/{userId}/{bookId}/{quantity}")
    public ResponseEntity<ShoppingCart> addItemToCart(@PathVariable int userId,@PathVariable long bookId,@PathVariable int quantity){
        return new ResponseEntity<>(cartService.addItemsToCart(userId,bookId,quantity),HttpStatus.CREATED);
    }

    @DeleteMapping("/items/{userId}/{itemId}")
    public ResponseEntity<String> removeItemFromCart(@PathVariable int userId , @PathVariable int itemId){
        return new ResponseEntity<>(cartService.removeItemFromCart(userId, itemId),HttpStatus.OK);
    }

    @GetMapping("/checkout/{userId}")
    public ShoppingCartCheckoutResult checkout(@PathVariable int userId) {
        ShoppingCartCheckoutResult result = cartService.checkout(userId);
        return result;
    }
}
