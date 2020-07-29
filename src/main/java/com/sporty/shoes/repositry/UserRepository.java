package com.sporty.shoes.repositry;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sporty.shoes.entity.User;
 
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
 
    @Query("SELECT u FROM User u WHERE u.username = :username")
    public User getUserByUsername(@Param("username") String username);
    
    @Query("select u from User u")
    Page<User> findAllByPage(Pageable pageable);

    
    Page<User> findAll(Pageable pageable);

}