package com.revature.business.review.site.controller;

import com.revature.business.review.site.entity.Business;
import com.revature.business.review.site.repository.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/business")
public class BusinessController {

    @Autowired
    private BusinessRepository businessRepository;

    @PostMapping("/create")
    public ResponseEntity<String> createBusiness(@RequestBody Business business){
        businessRepository.save(business);
        return ResponseEntity.ok("Business created successfully!");
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Business>> getAllBusinesses(){
        return ResponseEntity.ok(businessRepository.findAll());
    }
}


