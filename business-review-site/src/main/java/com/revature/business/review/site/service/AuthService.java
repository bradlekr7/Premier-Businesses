package com.revature.business.review.site.service;

import com.revature.business.review.site.entity.User;

public interface AuthService {

    void  registerUser(final User user);

    // add auth related methods like forgotPassword, changeRole ....
}
