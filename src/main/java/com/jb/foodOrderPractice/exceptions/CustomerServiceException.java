package com.jb.foodOrderPractice.exceptions;

public class CustomerServiceException extends Exception{
    public CustomerServiceException() {
        super("General customer service exception");
    }

    public CustomerServiceException(String message) {
        super(message);
    }
}
