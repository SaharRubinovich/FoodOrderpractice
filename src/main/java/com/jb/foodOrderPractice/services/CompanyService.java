package com.jb.foodOrderPractice.services;

import com.jb.foodOrderPractice.beans.Company;
import com.jb.foodOrderPractice.beans.Item;
import com.jb.foodOrderPractice.exceptions.CompanyServiceException;
import com.jb.foodOrderPractice.exceptions.ErrorMsg;
import com.jb.foodOrderPractice.exceptions.LoginException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CompanyService extends ClientType{
    private long companyId;

    @Override
    public boolean login(String email, String password) throws LoginException {
        if (!companyRepo.existsByEmail(email)){
            throw new LoginException(ErrorMsg.wasNotFound.toString());
        }
        if (companyRepo.findByEmailAndPassword(email, password).isPresent()){
            this.companyId = companyRepo.findByEmailAndPassword(email, password).get().getId();
            return true;
        }else {
            throw new LoginException(ErrorMsg.EmailOrPasswordWrong.toString());
        }
    }

    public void addItem(Item item) throws CompanyServiceException {
        if (itemRepo.findByTitleAndCompanyId(item.getTitle(), this.companyId).isPresent()   )
        {
            throw new CompanyServiceException(ErrorMsg.ItemAlreadyExist.toString());
        }
        item.setCompanyId(companyId);
        itemRepo.save(item);
        System.out.println("Item was saved");
    }

    public void updateItem(Item item) throws CompanyServiceException {
        if (itemRepo.findById(item.getId()).isEmpty()){
            throw new CompanyServiceException(ErrorMsg.ItemWasNotFound.toString());
        }
        itemRepo.saveAndFlush(item);
        System.out.println("Item was updated");
    }

    public void deleteItem(long itemId){
        itemRepo.deleteById(itemId);
        System.out.println("Item was deleted");
    }

    public Company getCompanyDetails(){
        return companyRepo.getById(companyId);
    }

    public List<Item> getItemsByCategory(String category){
        return companyRepo.getById(companyId).getItems().stream().filter(
                item -> item.getCategory().toString().equals(category)).collect(Collectors.toList());
    }

    public List<Item> getItemsByPrice(double maxPrice){
        return companyRepo.getById(companyId).getItems().stream().filter(
                item -> item.getPrice() <= maxPrice).collect(Collectors.toList());
    }
}
