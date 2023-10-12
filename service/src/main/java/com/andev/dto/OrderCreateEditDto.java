package com.andev.dto;

import com.andev.entity.enums.Payment;
import com.andev.entity.enums.Status;
import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Value
public class OrderCreateEditDto {
    LocalDate dateOrder;
    LocalDate dateClosing;
    @Min(1)
    Integer amount;
    @NotNull
    Payment payment;
    Status status;
    @NotBlank
    String town;
    @NotBlank
    String street;
    @Positive
    Integer houseNumber;
    Integer apartmentNumber;
    @Positive
    Integer postalCode;
    String username;
    @Positive
    Integer productId;
}
