package com.revature.business.review.site.service;

import com.revature.business.review.site.entity.Business;
import com.revature.business.review.site.entity.User;

import java.util.List;

public interface Service {

    User  registerUser(User user);
    Business registerBusiness(Business business);
    User authenticate(String userName,String password);
    List<Business> getAllBusiness();
    Business findBusinessById(Long businessId);

    // add auth related methods like forgotPassword, changeRole ....
    Business updateBusiness(long id, Business business);
    void deleteBusiness(long id);
}
