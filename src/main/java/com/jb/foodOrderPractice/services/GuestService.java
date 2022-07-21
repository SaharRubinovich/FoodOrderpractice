package com.jb.foodOrderPractice.services;

import com.jb.foodOrderPractice.beans.Company;
import com.jb.foodOrderPractice.beans.Customer;
import com.jb.foodOrderPractice.exceptions.CompanyServiceException;
import com.jb.foodOrderPractice.exceptions.CustomerServiceException;
import com.jb.foodOrderPractice.exceptions.ErrorMsg;
import com.jb.foodOrderPractice.repositories.CompanyRepo;
import com.jb.foodOrderPractice.repositories.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class GuestService {
    @Autowired
    private CompanyRepo companyRepo;
    @Autowired
    private CustomerRepo customerRepo;

    public void companyRegister(Company company) throws CompanyServiceException {
        if (companyRepo.existsByEmail(company.getEmail())){
            throw new CompanyServiceException(ErrorMsg.emailAlreadyExist.toString());
        }
        companyRepo.save(company);
        System.out.println("Company was registered");
    }

    public void customerRegister(Customer customer) throws CustomerServiceException {
        if (customerRepo.existsByEmail(customer.getEmail())){
            throw new CustomerServiceException(ErrorMsg.emailAlreadyExist.toString());
        }
        customerRepo.save(customer);
        System.out.println("Customer was registered");
    }
}
