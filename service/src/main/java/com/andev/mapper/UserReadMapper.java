package com.andev.mapper;

import com.andev.dto.UserReadDto;
import com.andev.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserReadMapper implements Mapper<User, UserReadDto> {

    @Override
    public UserReadDto map(User object) {
        return new UserReadDto(
                object.getId(),
                object.getUserName(),
                object.getFirstName(),
                object.getLastName(),
                object.getRole(),
                object.getImage(),
                object.getEmail(),
                object.getPhone());
    }
}
