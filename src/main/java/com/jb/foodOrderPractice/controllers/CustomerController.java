package com.jb.foodOrderPractice.controllers;

import com.jb.foodOrderPractice.beans.Item;
import com.jb.foodOrderPractice.exceptions.TokenException;
import com.jb.foodOrderPractice.exceptions.UnauthorizedException;
import com.jb.foodOrderPractice.services.CustomerService;
import com.jb.foodOrderPractice.util.JWTutil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "customer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomerController {
    private String newToken;
    private CustomerService customerService;
    private JWTutil jwTutil;

    @PutMapping(value = "/purchaseItem")
    public ResponseEntity<?> purchaseItems(@RequestHeader(name = "Authorization")String token, @RequestBody List<Item>itemList)
            throws TokenException, UnauthorizedException {
        if (jwTutil.validateToken(token,customerService.getCustomerDetails().getEmail())){
            customerService.purchaseItem(itemList);
            newToken = jwTutil.updateToken(token);
            return ResponseEntity.ok().header("Authorization", newToken).build();
        }
        throw new UnauthorizedException();
    }

    @GetMapping(value = "/getCustomerDetails")
    public ResponseEntity<?> getCustomerDetails(@RequestHeader(name = "Authorization")String token)
            throws TokenException, UnauthorizedException {
        if (jwTutil.validateToken(token,customerService.getCustomerDetails().getEmail())){
            newToken = jwTutil.updateToken(token);
            return ResponseEntity.ok()
                    .header("Authorization", newToken)
                    .body(customerService.getCustomerDetails());
        }
        throw new UnauthorizedException();
    }

    @GetMapping(value = "/getCustomerItemsByCategory")
    public ResponseEntity<?> getCustomerItemsByCategory(@RequestHeader(name = "Authorization")String token, String category)
            throws TokenException, UnauthorizedException {
        if (jwTutil.validateToken(token,customerService.getCustomerDetails().getEmail())){
            newToken = jwTutil.updateToken(token);
            return ResponseEntity.ok()
                    .header("Authorization", newToken)
                    .body(customerService.getItemsByCategory(category));
        }
        throw new UnauthorizedException();
    }

    @GetMapping(value = "/getCustomerItemsByPrice")
    public ResponseEntity<?> getCustomerItemsByPrice(@RequestHeader(name = "Authorization")String token,double maxPrice)
            throws TokenException, UnauthorizedException {
        if (jwTutil.validateToken(token,customerService.getCustomerDetails().getEmail())){
            newToken = jwTutil.updateToken(token);
            return ResponseEntity.ok()
                    .header("Authorization", newToken)
                    .body(customerService.getItemsByPrice(maxPrice));
        }
        throw new UnauthorizedException();
    }
}
