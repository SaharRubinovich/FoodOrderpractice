package com.jb.foodOrderPractice.advice;

import com.jb.foodOrderPractice.exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class UnauthorizedAdvice {
    @ExceptionHandler(value = {UnauthorizedException.class})
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public ErrorDetail unauthorizedError(Exception err){
        return new ErrorDetail("Unauthorized: ", err.getMessage());
    }
}
