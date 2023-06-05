package com.andev.dao;

import com.andev.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends
        JpaRepository<User,Integer>,
        UserFilterRepository,
        QuerydslPredicateExecutor<User> {

   Optional<User> findByUserName(String username);
}
