package com.jb.foodOrderPractice.exceptions;

public class UnauthorizedException extends Exception{
    public UnauthorizedException() {
        super(ErrorMsg.Unauthorized.toString());
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}
