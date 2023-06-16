package com.andev.dto;

import com.andev.entity.enums.Category;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Value
@FieldNameConstants
public class ProductCreateEditDto {
    @NotBlank
    String name;

    @NotBlank
    String model;

    @NotNull
    Category category;

    String description;

    @Positive
    BigDecimal price;

    @Min(1)
    Integer amount;

    Integer manufacturerId;

    MultipartFile image;
}
