package com.andev.dao;

import com.andev.dto.UserFilter;
import com.andev.entity.User;

import java.util.List;

public interface UserFilterRepository {
    List<User> findAllByFilter (UserFilter filter);
}
