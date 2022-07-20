package com.jb.foodOrderPractice.controllers;

import com.jb.foodOrderPractice.beans.Company;
import com.jb.foodOrderPractice.beans.Customer;
import com.jb.foodOrderPractice.exceptions.AdminServiceException;
import com.jb.foodOrderPractice.exceptions.TokenException;
import com.jb.foodOrderPractice.exceptions.UnauthorizedException;
import com.jb.foodOrderPractice.services.AdminService;
import com.jb.foodOrderPractice.util.JWTutil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "admin")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminController {
    private AdminService adminService;
    private JWTutil jwTutil;
    private String newToken;

    @PostMapping(value = "/addCompany")
    public ResponseEntity<?> addCompany(@RequestHeader(name = "Authorization") String token, @RequestBody Company company)
            throws TokenException, AdminServiceException, UnauthorizedException {
        if (jwTutil.validateToken(token, adminService.getADMIN_EMAIL())){
            adminService.addCompany(company);
            newToken = jwTutil.updateToken(token);
            return ResponseEntity.ok().header("Authorization",newToken).build();
        }
        throw new UnauthorizedException();
    }

    @PutMapping(value = "/updateCompany")
    public ResponseEntity<?> updateCompany(@RequestHeader(name = "Authorization") String token, @RequestBody Company company)
            throws TokenException, AdminServiceException, UnauthorizedException {
        if (jwTutil.validateToken(token, adminService.getADMIN_EMAIL())){
            adminService.updateCompany(company);
            newToken = jwTutil.updateToken(token);
            return ResponseEntity.ok().header("Authorization",newToken).build();
        }
        throw new UnauthorizedException();
    }

    @DeleteMapping(value = "/deleteCompany/{id}")
    public ResponseEntity<?> deleteCompany(@RequestHeader(name = "Authorization") String token, @PathVariable long id)
            throws TokenException, UnauthorizedException {
        if (jwTutil.validateToken(token, adminService.getADMIN_EMAIL())){
            adminService.deleteCompany(id);
            newToken = jwTutil.updateToken(token);
            return ResponseEntity.ok().header("Authorization", newToken).build();
        }
        throw new UnauthorizedException();
    }

    @GetMapping(value = "/getAllCompanies")
    public ResponseEntity<?> getAllCompanies(@RequestHeader(name = "Authorization") String token)
            throws UnauthorizedException, TokenException {
        if (jwTutil.validateToken(token, adminService.getADMIN_EMAIL())){
            newToken = jwTutil.updateToken(token);
            return ResponseEntity.ok()
                    .header("Authorization", newToken)
                    .body(adminService.getAllCompanies());
        }
        throw new UnauthorizedException();
    }

    @GetMapping(value = "/getCompany/{id}")
    public ResponseEntity<?> getSingleCompany(@RequestHeader(name = "Authorization")String token, @PathVariable long id)
            throws TokenException, AdminServiceException, UnauthorizedException {
        if (jwTutil.validateToken(token, adminService.getADMIN_EMAIL())){
            newToken = jwTutil.updateToken(token);
            return ResponseEntity.ok()
                    .header("Authorization", newToken)
                    .body(adminService.getSingleCompany(id));
        }
        throw new UnauthorizedException();
    }

    @PostMapping(value = "/addCustomer")
    public ResponseEntity<?> addCustomer(@RequestHeader(name = "Authorization")String token, @RequestBody Customer customer)
            throws TokenException, AdminServiceException, UnauthorizedException {
        if (jwTutil.validateToken(token, adminService.getADMIN_EMAIL())){
            adminService.addCustomer(customer);
            newToken = jwTutil.updateToken(token);
            return ResponseEntity.ok()
                    .header("Authorization", newToken)
                    .build();
        }
        throw new UnauthorizedException();
    }

    @PutMapping(value = "/updateCustomer")
    public ResponseEntity<?> updateCustomer(@RequestHeader(name = "Authorization")String token, @RequestBody Customer customer)
            throws TokenException, AdminServiceException, UnauthorizedException {
        if (jwTutil.validateToken(token, adminService.getADMIN_EMAIL())){
            adminService.updateCustomer(customer);
            newToken = jwTutil.updateToken(token);
            return ResponseEntity.ok()
                    .header("Authorization", newToken)
                    .build();
        }
        throw new UnauthorizedException();
    }

    @DeleteMapping(value = "/deleteCustomer/{id}")
    public ResponseEntity<?> deleteCustomer(@RequestHeader(name = "Authorization")String token, @PathVariable long id)
            throws TokenException, UnauthorizedException {
        if (jwTutil.validateToken(token, adminService.getADMIN_EMAIL())){
            adminService.deleteCustomer(id);
            newToken = jwTutil.updateToken(token);
            return ResponseEntity.ok()
                    .header("Authorization", newToken)
                    .build();
        }
        throw new UnauthorizedException();
    }

    @GetMapping(value = "/getAllCustomers")
    public ResponseEntity<?> getAllCustomers(@RequestHeader(name = "Authorization")String token)
            throws TokenException, UnauthorizedException {
        if (jwTutil.validateToken(token, adminService.getADMIN_EMAIL())){
            newToken = jwTutil.updateToken(token);
            return ResponseEntity.ok()
                    .header("Authorization")
                    .body(adminService.getAllCustomers());
        }
        throw new UnauthorizedException();
    }

    @GetMapping(value = "/getSingleCustomer/{id}")
    public ResponseEntity<?> getSingleCustomer(@RequestHeader(name = "Authorization")String token,@PathVariable long id)
            throws TokenException, AdminServiceException, UnauthorizedException {
        if (jwTutil.validateToken(token, adminService.getADMIN_EMAIL())){
            newToken = jwTutil.updateToken(token);
            return ResponseEntity.ok()
                    .header("Authorization", newToken)
                    .body(adminService.getSingleCustomer(id));
        }
        throw new UnauthorizedException();
    }
}
