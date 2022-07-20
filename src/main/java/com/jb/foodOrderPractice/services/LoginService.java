package com.jb.foodOrderPractice.services;

import com.jb.foodOrderPractice.exceptions.LoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private AdminService adminService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CustomerService customerService;

    public ClientType login(String userEmail, String userPass, String userType) throws LoginException {
        ClientType clientType = null;
        if (userType.equalsIgnoreCase("Admin") && adminService.login(userEmail,userPass)){
            return clientType = adminService;
        }
        if (userType.equalsIgnoreCase("Company") && companyService.login(userEmail,userPass)){
            return clientType = companyService;
        }
        if (userType.equalsIgnoreCase("Customer") && customerService.login(userEmail,userPass)){
            return clientType = customerService;
        }
        return clientType;
    }
}
