package com.sporty.shoes.repositry;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sporty.shoes.entity.Product;
import com.sporty.shoes.entity.User;
 

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
 
    @Query("SELECT p FROM Product p WHERE p.productname = :productname")
    public Product getProductByName(@Param("productname") String productname);
 
    @Query("select p from Product p")
    Page<Product> findAllByPage(Pageable pageable);
}