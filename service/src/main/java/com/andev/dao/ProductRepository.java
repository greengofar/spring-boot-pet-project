package com.andev.dao;

import com.andev.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends
        JpaRepository<Product,Integer>,
        ProductFilterRepository,
        QuerydslPredicateExecutor<Product> {
   }
