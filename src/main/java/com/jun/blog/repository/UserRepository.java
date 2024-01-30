package com.jun.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jun.blog.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

   boolean existsByUserId(Integer userId);

   boolean existsByEmail(String email);

   boolean existsByUserName(String userName);
   
   User findByEmail(String email);

   User findByUserId(Integer userId);

   User findByUserName(String userName);

}
