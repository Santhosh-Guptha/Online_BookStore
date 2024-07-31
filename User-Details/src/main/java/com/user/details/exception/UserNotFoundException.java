package com.user.details.exception;

public class UserNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public UserNotFoundException() {
        super("User Not Found");
    }

    public UserNotFoundException(String message){
        super(message);
    }
}
