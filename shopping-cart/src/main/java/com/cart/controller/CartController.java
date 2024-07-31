package com.cart.controller;

import com.cart.entity.ShoppingCart;
import com.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

    @PostMapping("/addbook/{userId}/{bookId}/{quantity}")
    public ResponseEntity<ShoppingCart> addBookToCart(@PathVariable int userId,@PathVariable int bookId,@PathVariable int quantity){
        return new ResponseEntity<>(cartService.addBookToCart(userId,bookId,quantity),HttpStatus.OK);
    }

}
