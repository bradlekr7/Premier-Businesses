package com.revature.business.review.site.repository;

import com.revature.business.review.site.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
  Optional<User> findByUsername(String username);

  @Query("Select t from User t where username = :username and password = :password")
  List<User> findByUserNameAndPassword(@Param("username") String username,@Param("password") String password);


}

