package com.jb.foodOrderPractice.advice;

import com.jb.foodOrderPractice.exceptions.AdminServiceException;
import com.jb.foodOrderPractice.exceptions.CompanyServiceException;
import com.jb.foodOrderPractice.exceptions.CustomerServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ClientTypeAdvice {
    @ExceptionHandler(value = {AdminServiceException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDetail adminError(Exception err){
        return new ErrorDetail("Admin error: ", err.getMessage());
    }

    @ExceptionHandler(value = {CompanyServiceException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDetail companyError(Exception err){
        return new ErrorDetail("Company error: ", err.getMessage());
    }

    @ExceptionHandler(value = {CustomerServiceException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDetail customerError(Exception err){
        return new ErrorDetail("Customer error: ", err.getMessage());
    }
}
