package com.jb.foodOrderPractice.exceptions;

public class LoginException extends Exception{
    public LoginException() {
        super("general login exception");
    }

    public LoginException(String message) {
        super(message);
    }
}
