package com.jb.foodOrderPractice.services;

import com.jb.foodOrderPractice.exceptions.CompanyServiceException;
import com.jb.foodOrderPractice.exceptions.LoginException;
import com.jb.foodOrderPractice.repositories.CompanyRepo;
import com.jb.foodOrderPractice.repositories.CustomerRepo;
import com.jb.foodOrderPractice.repositories.ItemRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public abstract class ClientType {
    @Autowired
    protected CompanyRepo companyRepo;
    @Autowired
    protected CustomerRepo customerRepo;
    @Autowired
    protected ItemRepo itemRepo;

    public abstract boolean login(String email, String password) throws LoginException;
}
