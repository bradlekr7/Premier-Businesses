package com.revature.business.review.site;

import com.revature.business.review.site.entity.Role;
import com.revature.business.review.site.repository.RoleRepository;
import com.revature.business.review.site.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BusinessReviewSiteApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BusinessReviewSiteApplication.class, args);
	}
	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {

		//creating roles
		Role businessRole= new Role(1L,"BUSINESS");
		Role customerRole= new Role(2L,"CUSTOMER");

		List<Role> roles= new ArrayList<>();
		roles.add(businessRole);
		roles.add(customerRole);

		List<Role> allRoles = roleRepository.findAll();
		if(roles.isEmpty()) {
			roleRepository.saveAll(roles);
		}
	}
}
