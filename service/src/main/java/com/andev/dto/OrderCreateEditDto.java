package com.andev.dto;

import com.andev.entity.enums.Payment;
import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Value
public class OrderCreateEditDto {
    String username;

    @Positive
    Integer productId;

    @Min(1)
    Integer amount;

    @NotNull
    Payment payment;

    @NotBlank
    String town;

    @NotBlank
    String street;

    @Positive
    Integer houseNumber;

    Integer apartmentNumber;

    @Positive
    Integer postalCode;
}
