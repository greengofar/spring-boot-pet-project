package com.andev.dto;

import com.andev.entity.enums.Category;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class ProductReadDto {
    Integer id;
    String name;
    String model;
    Category category;
    String description;
    BigDecimal price;
    Integer amount;
    ManufacturerReadDto manufacturer;
}
