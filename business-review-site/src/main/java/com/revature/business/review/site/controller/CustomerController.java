package com.revature.business.review.site.controller;

import com.revature.business.review.site.entity.User;
import com.revature.business.review.site.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<String> createBusiness(@RequestBody User customer) {
        userRepository.save(customer);
        return ResponseEntity.ok("Customer created successfully!");
    }
}