package com.revature.business.review.site.repository;

import com.revature.business.review.site.entity.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BusinessRepository extends JpaRepository<Business,Long> {
}
