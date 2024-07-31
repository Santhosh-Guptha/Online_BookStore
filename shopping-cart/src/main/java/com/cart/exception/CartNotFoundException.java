package com.cart.exception;

public class CartNotFoundException extends RuntimeException{
    public CartNotFoundException() {
        super("Cart Not Created or Found With the given Id.");
    }

    public CartNotFoundException(String message) {
        super(message);
    }
}
