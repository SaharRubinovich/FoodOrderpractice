package com.jb.foodOrderPractice.exceptions;

public class CompanyServiceException extends Exception{
    public CompanyServiceException() {
        super("General company service exception");
    }

    public CompanyServiceException(String message) {
        super(message);
    }
}
