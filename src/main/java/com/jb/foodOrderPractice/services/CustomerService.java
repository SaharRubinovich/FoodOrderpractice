package com.jb.foodOrderPractice.services;

import com.jb.foodOrderPractice.beans.Customer;
import com.jb.foodOrderPractice.beans.Item;
import com.jb.foodOrderPractice.exceptions.CompanyServiceException;
import com.jb.foodOrderPractice.exceptions.ErrorMsg;
import com.jb.foodOrderPractice.exceptions.LoginException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService extends ClientType{
    private long customerId;

    @Override
    public boolean login(String email, String password) throws LoginException {
        if (!customerRepo.existsByEmail(email)){
            throw new LoginException(ErrorMsg.wasNotFound.toString());
        }
        if (customerRepo.findByEmailAndPassword(email, password).isPresent()){
            this.customerId = customerRepo.findByEmailAndPassword(email, password).get().getId();
            return true;
        } else {
            throw new LoginException(ErrorMsg.EmailOrPasswordWrong.toString());
        }
    }

    public void purchaseItem(List<Item> itemList){
        Customer customerTemp = customerRepo.getById(customerId);
        customerTemp.getItems().addAll(itemList);
        customerRepo.saveAndFlush(customerTemp);
    }

    public Customer getCustomerDetails(){
        return customerRepo.getById(customerId);
    }

    public List<Item> getItemsByCategory(String category){
        return customerRepo.getById(customerId).getItems().stream().filter(
                item -> item.getCategory().toString().equals(category)
        ).collect(Collectors.toList());
    }

    public List<Item> getItemsByPrice(double maxPrice){
        return customerRepo.getById(customerId).getItems().stream().filter(
                item -> item.getPrice() <= maxPrice
        ).collect(Collectors.toList());
    }
}
