package com.jb.foodOrderPractice.controllers;

import com.jb.foodOrderPractice.beans.UserDetails;
import com.jb.foodOrderPractice.exceptions.LoginException;
import com.jb.foodOrderPractice.services.LoginService;
import com.jb.foodOrderPractice.util.JWTutil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {
    private LoginService loginService;
    private JWTutil jwTutil;

    @PostMapping(value = "login")
    public ResponseEntity<?> userLogin(@RequestBody UserDetails userDetails) throws LoginException {
        if (loginService.login(userDetails.getUserEmail(), userDetails.getUserPass(), userDetails.getUserType()) != null){
            return ResponseEntity.ok().header("Authorization", jwTutil.generateToken(userDetails)).build();
        }
        throw new LoginException();
    }
}
