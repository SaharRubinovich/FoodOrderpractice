package com.jb.foodOrderPractice.exceptions;

import lombok.ToString;

@ToString
public enum ErrorMsg {
    emailAlreadyExist("User with the same email already exist in the system."),
    wasNotFound("The user was not found in the system"),
    ItemAlreadyExist("Item with the same title already exists in the system"),
    ItemWasNotFound("Item was not found in the system"),
    EmailOrPasswordWrong("Email or Password are incorrect"),
    Unauthorized("User is not authorized to preform the wanted operation");

    private final String text;


    ErrorMsg(String text) {
        this.text = text;
    }
}
