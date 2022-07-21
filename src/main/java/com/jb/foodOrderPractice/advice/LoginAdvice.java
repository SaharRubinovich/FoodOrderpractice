package com.jb.foodOrderPractice.advice;

import com.jb.foodOrderPractice.exceptions.LoginException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class LoginAdvice {
    @ExceptionHandler(value = {LoginException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDetail loginError(Exception err){
        return new ErrorDetail("Login error: ", err.getMessage());
    }
}
