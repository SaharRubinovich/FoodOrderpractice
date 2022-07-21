package com.jb.foodOrderPractice.advice;

import com.jb.foodOrderPractice.exceptions.TokenException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class TokenAdvice {

    @ExceptionHandler(value = {TokenException.class})
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    public ErrorDetail tokenError(Exception err){
        return new ErrorDetail("Token Error", err.getMessage());
    }
}
