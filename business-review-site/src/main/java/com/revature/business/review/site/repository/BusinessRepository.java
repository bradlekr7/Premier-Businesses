package com.revature.business.review.site.repository;

import com.revature.business.review.site.entity.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BusinessRepository extends JpaRepository<Business,Long> {

    @Query("SELECT b FROM Business b WHERE b.businessType LIKE :type")
    public List<Business> search(@Param("type") String type);
}
