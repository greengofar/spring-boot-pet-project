package com.andev.mapper;

import com.andev.dto.ManufacturerReadDto;
import com.andev.dto.ProductReadDto;
import com.andev.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductReadMapper implements Mapper<Product, ProductReadDto> {

    private final ManufacturerReadMapper manufacturerReadMapper;
    @Override
    public ProductReadDto map(Product object) {
        ManufacturerReadDto manufacturerReadDto = Optional.ofNullable(object.getManufacturer())
                .map(manufacturerReadMapper::map)
                .orElse(null);
        return new ProductReadDto(
                object.getId(),
                object.getName(),
                object.getModel(),
                object.getCategory(),
                object.getDescription(),
                object.getPrice(),
                object.getAmount(),
                manufacturerReadDto
        );
    }
}
