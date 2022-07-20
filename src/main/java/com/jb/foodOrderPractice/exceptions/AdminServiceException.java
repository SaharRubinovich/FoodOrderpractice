package com.jb.foodOrderPractice.exceptions;

public class AdminServiceException extends Exception{
    public AdminServiceException() {
        super("Admin Error");
    }

    public AdminServiceException(String message) {
        super(message);
    }
}
