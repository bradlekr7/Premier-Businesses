package com.revature.business.review.site.service;

import com.revature.business.review.site.entity.Business;
import com.revature.business.review.site.entity.User;
import com.revature.business.review.site.repository.BusinessRepository;
import com.revature.business.review.site.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceImpl implements Service {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BusinessRepository businessRepository;



    @Override
    public User registerUser(User user) {
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Business registerBusiness(Business business) {
        return businessRepository.save(business);
    }

    @Override
    public User authenticate(String userName,String password) {
        List<User> lstUser = userRepository.findByUserNameAndPassword(userName,password);
        if(lstUser != null && lstUser.size() > 0) {
            return lstUser.get(0);
        }
        return null;
    }

    @Override
    public List<Business> getAllBusiness() {
        return businessRepository.findAll();
    }

    @Override
    public Business findBusinessById(Long businessId) {
        Business business = businessRepository.findById(businessId).get();
        return business;
    }


}
