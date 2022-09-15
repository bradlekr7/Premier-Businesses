package com.revature.business.review.site.controller;

import com.revature.business.review.site.entity.Business;
import com.revature.business.review.site.entity.User;
import com.revature.business.review.site.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class BusinessController {

    @Autowired
    private Service service;

    @PostMapping("/registerUser")
    public ResponseEntity<Map<String,Object>> registerUser(@RequestBody Map<String,String> mapData){
        Map<String,Object> mapRet = new HashMap<String,Object>();
        User user = new User();
        user.setName(mapData.get("name"));
        user.setUsername(mapData.get("username"));
        user.setPassword(mapData.get("password"));
        user.setContactNumber(mapData.get("contactNumber"));
        User sendUser = service.registerUser(user);
        Business business = new Business();
        business.setBusinessName(mapData.get("businessName"));
        business.setBusinessType(mapData.get("businessType"));
        business.setBusinessContactInfo(mapData.get("businessContactInfo"));
        business.setWebLink(mapData.get("webLink"));
        business.setDescription(mapData.get("description"));
        business.setUser(sendUser);
        Business sendBusiness = service.registerBusiness(business);
        mapRet.put("user",sendUser);
        mapRet.put("business",sendBusiness);
        return ResponseEntity.ok(mapRet);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<User> authenticate(@RequestBody Map<String,String> mapData){
        User user = service.authenticate(mapData.get("username"),mapData.get("password"));
        return ResponseEntity.ok(user);
    }

    @PostMapping("/getAllBusiness")
    public ResponseEntity<List<Business>> getAllBusiness(){
        List<Business> lstData = service.getAllBusiness();
        return ResponseEntity.ok(lstData);
    }

    @PostMapping("/findBusinessById")
    public ResponseEntity<Business> findBusinessById(@RequestBody Map<String,String> mapData){
        Long businessId = Long.parseLong(mapData.get("businessId"));
        Business business = service.findBusinessById(businessId);
        return ResponseEntity.ok(business);
    }




}


