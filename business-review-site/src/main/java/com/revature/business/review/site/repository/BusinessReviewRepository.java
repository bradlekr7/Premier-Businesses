package com.revature.business.review.site.repository;


import com.revature.business.review.site.entity.BusinessReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessReviewRepository extends JpaRepository<BusinessReview, Long> {
}
