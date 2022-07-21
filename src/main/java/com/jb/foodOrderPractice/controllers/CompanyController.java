package com.jb.foodOrderPractice.controllers;

import com.jb.foodOrderPractice.beans.Item;
import com.jb.foodOrderPractice.exceptions.CompanyServiceException;
import com.jb.foodOrderPractice.exceptions.TokenException;
import com.jb.foodOrderPractice.exceptions.UnauthorizedException;
import com.jb.foodOrderPractice.services.CompanyService;
import com.jb.foodOrderPractice.util.JWTutil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "company")
public class CompanyController {
    private CompanyService companyService;
    String newToken;
    private JWTutil jwTutil;

    @PostMapping(value = "/addItem")
    public ResponseEntity<?> addItem(@RequestHeader(name = "Authorization")String token, @RequestBody Item item)
            throws TokenException, CompanyServiceException, UnauthorizedException {
        if (jwTutil.validateToken(token, companyService.getCompanyDetails().getEmail())){
            companyService.addItem(item);
            newToken = jwTutil.updateToken(token);
            return ResponseEntity.ok().header("Authorization", newToken).build();
        }
        throw new UnauthorizedException();
    }

    @PutMapping(value = "/updateItem")
    public ResponseEntity<?> updateItem(@RequestHeader(name = "Authorization")String token, Item item)
            throws TokenException, CompanyServiceException, UnauthorizedException {
        if (jwTutil.validateToken(token,companyService.getCompanyDetails().getEmail())){
            companyService.updateItem(item);
            newToken = jwTutil.updateToken(token);
            return ResponseEntity.ok().header("Authorization", newToken).build();
        }
        throw new UnauthorizedException();
    }

    @DeleteMapping(value = "/deleteItem/{id}")
    public ResponseEntity<?> deleteItem(@RequestHeader(name = "Authorization")String token,@PathVariable long id)
            throws TokenException, UnauthorizedException {
        if (jwTutil.validateToken(token,companyService.getCompanyDetails().getEmail())){
            companyService.deleteItem(id);
            newToken = jwTutil.updateToken(token);
            return ResponseEntity.ok().header("Authorization", newToken).build();
        }
        throw new UnauthorizedException();
    }

    @GetMapping(value = "/getCompanyDetails")
    public ResponseEntity<?> getCompanyDetails(@RequestHeader(name = "Authorization")String token)
            throws TokenException, UnauthorizedException {
        if (jwTutil.validateToken(token,companyService.getCompanyDetails().getEmail())){
            newToken = jwTutil.updateToken(token);
            return ResponseEntity.ok()
                    .header("Authorization",newToken)
                    .body(companyService.getCompanyDetails());
        }
        throw new UnauthorizedException();
    }

    @GetMapping(value = "/getCompanyItemsByCategory")
    public ResponseEntity<?> getCompanyItemsByCategory(@RequestHeader(name = "Authorization")String token,String category)
            throws TokenException, UnauthorizedException {
        if (jwTutil.validateToken(token,companyService.getCompanyDetails().getEmail())){
            newToken = jwTutil.updateToken(token);
            return ResponseEntity.ok()
                    .header("Authorization", newToken)
                    .body(companyService.getItemsByCategory(category));
        }
        throw new UnauthorizedException();
    }

    @GetMapping(value = "/getCompanyItemsByMaxPrice")
    public ResponseEntity<?> getCompanyItemsByMaxPrice(@RequestHeader(name = "Authorization")String token, double maxPrice)
            throws TokenException, UnauthorizedException {
        if (jwTutil.validateToken(token,companyService.getCompanyDetails().getEmail())){
            newToken = jwTutil.updateToken(token);
            return ResponseEntity.ok()
                    .header("Authorization", newToken)
                    .body(companyService.getItemsByPrice(maxPrice));
        }
        throw new UnauthorizedException();
    }
}
