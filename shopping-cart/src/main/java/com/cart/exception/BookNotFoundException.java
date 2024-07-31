package com.cart.exception;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(String message) {
        super(message);
    }

    public BookNotFoundException() {
        super("Book Not Found With The Given Id.");
    }
}
