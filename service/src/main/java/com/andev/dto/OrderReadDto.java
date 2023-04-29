package com.andev.dto;

import com.andev.entity.UserAddress;
import com.andev.entity.enums.Payment;
import com.andev.entity.enums.Status;
import lombok.Value;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Value
public class OrderReadDto {

    Integer id;
    LocalDate dateOrder;
    LocalDate dateClosing;
    Integer totalValue;
    @Enumerated(EnumType.STRING)
    Payment payment;
    @Enumerated(EnumType.STRING)
    Status status;
    UserAddress userAddress;
    List<ProductReadDto> products = new ArrayList<>();
}
