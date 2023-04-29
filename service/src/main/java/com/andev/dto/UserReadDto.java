package com.andev.dto;

import lombok.Value;

@Value
public class UserReadDto {
    Integer id;
    String userName;
    String firstName;
    String lastName;
    String image;
    String email;
    String phone;
   }
