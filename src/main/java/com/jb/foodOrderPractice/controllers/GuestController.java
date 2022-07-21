package com.jb.foodOrderPractice.controllers;

import com.jb.foodOrderPractice.beans.Company;
import com.jb.foodOrderPractice.beans.Customer;
import com.jb.foodOrderPractice.exceptions.CompanyServiceException;
import com.jb.foodOrderPractice.exceptions.CustomerServiceException;
import com.jb.foodOrderPractice.services.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "guest")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GuestController {
    private GuestService guestService;

    @PostMapping(value = "/companyRegister")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void registerCompany(@RequestBody Company company) throws CompanyServiceException {
        guestService.companyRegister(company);
    }

    @PostMapping(value = "/customerRegister")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void registerCustomer(@RequestBody Customer customer) throws CustomerServiceException {
        guestService.customerRegister(customer);
    }
}
