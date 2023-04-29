package com.andev.dto;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class ProductReadDto {
    Integer id;
    String name;
    String model;
    BigDecimal price;
    ManufacturerReadDto manufacturerReadDto;

}
