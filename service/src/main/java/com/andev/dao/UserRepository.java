package com.andev.dao;

import com.andev.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>,UserFilterRepository {

   Optional<User> findByUserName(String username);
}
