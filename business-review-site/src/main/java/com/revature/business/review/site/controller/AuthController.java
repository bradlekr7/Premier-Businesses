package com.revature.business.review.site.controller;

import com.revature.business.review.site.entity.User;
import com.revature.business.review.site.service.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final Service service;


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody final User user) {
        service.registerUser(user);
        return ResponseEntity.ok("You are successfully registered!!!");

    }




}
