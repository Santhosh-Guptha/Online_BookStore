package com.cart.exception;

import java.io.Serial;

public class UserNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public UserNotFoundException() {
        super("User Not Found");
    }

    public UserNotFoundException(String message){
        super(message);
    }
}
