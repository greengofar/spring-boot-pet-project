package com.andev.dto;

import com.andev.entity.enums.Role;
import lombok.Value;

@Value
public class UserReadDto {
    Integer id;
    String userName;
    String firstName;
    String lastName;
    Role role;
    String image;
    String email;
    String phone;
   }
