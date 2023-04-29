package com.andev.dto;

import com.andev.entity.enums.Category;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class ProductFilter {
    String manufacturerName;
    String productName;
    BigDecimal priceMin;
    BigDecimal priceMax;

    Category category;
}
