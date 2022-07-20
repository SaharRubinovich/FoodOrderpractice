package com.jb.foodOrderPractice.services;

import com.jb.foodOrderPractice.beans.Company;
import com.jb.foodOrderPractice.beans.Customer;
import com.jb.foodOrderPractice.exceptions.AdminServiceException;
import com.jb.foodOrderPractice.exceptions.ErrorMsg;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService extends ClientType{
    private final String ADMIN_EMAIL = "admin@admin.com";
    private final String ADMIN_PASS = "admin";

    public String getADMIN_EMAIL() {
        return ADMIN_EMAIL;
    }

    @Override
    public boolean login(String email, String password) {
        return email.equals(ADMIN_EMAIL) && password.equals(ADMIN_PASS);
    }

    public void addCompany(Company company) throws AdminServiceException {
        if (companyRepo.existsByEmail(company.getEmail())){
            throw new AdminServiceException(ErrorMsg.emailAlreadyExist.toString());
        }
        companyRepo.save(company);
        System.out.println("Company was added");
    }

    public void updateCompany(Company company) throws AdminServiceException {
        if (companyRepo.findById(company.getId()).isEmpty()){
            throw new AdminServiceException(ErrorMsg.wasNotFound.toString());
        }
        companyRepo.saveAndFlush(company);
        System.out.println("Company was updated");
    }

    public void deleteCompany(long company_id){
        itemRepo.deleteItemPurchase(company_id);
        itemRepo.deleteAllByCompanyId(company_id);
        companyRepo.deleteById(company_id);
        System.out.println("Company was deleted");
    }

    public List<Company> getAllCompanies(){
        return companyRepo.findAll();
    }

    public Company getSingleCompany(long companyId) throws AdminServiceException {
        if (companyRepo.findById(companyId).isEmpty()){
            throw new AdminServiceException(ErrorMsg.wasNotFound.toString());
        }
        return companyRepo.getById(companyId);
    }


    public void addCustomer(Customer customer) throws AdminServiceException {
        if (customerRepo.existsByEmail(customer.getEmail())){
            throw new AdminServiceException(ErrorMsg.emailAlreadyExist.toString());
        }
        customerRepo.save(customer);
        System.out.println("Customer was added");
    }

    public void updateCustomer(Customer customer) throws AdminServiceException {
        if (customerRepo.findById(customer.getId()).isEmpty()){
            throw new AdminServiceException(ErrorMsg.wasNotFound.toString());
        }
        customerRepo.saveAndFlush(customer);
        System.out.println("Customer was updated");
    }

    public void deleteCustomer(long customer_id){
        customerRepo.deleteById(customer_id);
        System.out.println("Customer was deleted");
    }

    public List<Customer> getAllCustomers(){
        return customerRepo.findAll();
    }

    public Customer getSingleCustomer(long customerId) throws AdminServiceException {
        if (customerRepo.findById(customerId).isEmpty()){
            throw new AdminServiceException(ErrorMsg.wasNotFound.toString());
        }
        return customerRepo.getById(customerId);
    }
}
