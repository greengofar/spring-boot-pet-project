package com.andev.dto;

import com.andev.entity.UserAddress;
import com.andev.entity.enums.Payment;
import com.andev.entity.enums.Status;
import lombok.Value;

import java.time.LocalDate;

@Value
public class OrderReadDto {
    Integer id;
    LocalDate dateOrder;
    LocalDate dateClosing;
    ProductReadDto product;
    Integer amount;
    UserAddress userAddress;
    Payment payment;
    Status status;
    Integer userId;
}
