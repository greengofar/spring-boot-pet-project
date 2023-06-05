package com.andev.dto;

import com.andev.entity.enums.Category;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Value
@FieldNameConstants
public class ProductCreateEditDto {
    @NotBlank
    String name;

    @NotBlank
    String model;

    Category category;

    String description;

    @Min(0)
    BigDecimal price;

    @Min(1)
    Integer amount;

    Integer manufacturerId;
}
