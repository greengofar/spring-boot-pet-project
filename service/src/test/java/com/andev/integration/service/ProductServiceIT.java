package com.andev.integration.service;

import com.andev.dto.ProductCreateEditDto;
import com.andev.dto.ProductReadDto;
import com.andev.entity.enums.Category;
import com.andev.integration.IntegrationTestBase;
import com.andev.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
class ProductServiceIT extends IntegrationTestBase {

    private final ProductService productService;
    private static final Integer PRODUCT_1 = 1;
    private static final Integer MANUFACTURER_1 = 1;

    @Test
    void findAll() {
        List<ProductReadDto> result = productService.findAll();
        assertThat(result).hasSize(6);
    }

    @Test
    void findById() {
        Optional<ProductReadDto> result = productService.findById(PRODUCT_1);
        assertTrue(result.isPresent());
        result.ifPresent(product -> assertEquals("iPhone 11", product.getModel()));
    }

    @Test
    void create() {
        ProductCreateEditDto productDto = new ProductCreateEditDto(
                "test",
                "test",
                Category.COMPUTERS,
                "test description",
                BigDecimal.ONE,
                Integer.valueOf(1000),
                MANUFACTURER_1
        );
        ProductReadDto actualResult = productService.create(productDto);
        assertEquals(productDto.getName(), actualResult.getName());
        assertEquals(productDto.getModel(), actualResult.getModel());
        assertSame(productDto.getCategory(), actualResult.getCategory());
        assertEquals(productDto.getDescription(), actualResult.getDescription());
        assertEquals(productDto.getPrice(), actualResult.getPrice());
        assertEquals(productDto.getPrice(), actualResult.getPrice());
        assertEquals(productDto.getManufacturerId(), actualResult.getManufacturer().id());
    }

    @Test
    void update() {
        ProductCreateEditDto productDto = new ProductCreateEditDto(
                "test",
                "test",
                Category.COMPUTERS,
                "test description",
                BigDecimal.ONE,
                Integer.valueOf(1000),
                MANUFACTURER_1
        );
        Optional<ProductReadDto> actualResult = productService.update(PRODUCT_1, productDto);
        assertThat(actualResult.isPresent());

        actualResult.ifPresent(product -> {
            assertEquals(productDto.getName(), product.getName());
            assertEquals(productDto.getModel(), product.getModel());
            assertSame(productDto.getCategory(), product.getCategory());
            assertEquals(productDto.getDescription(), product.getDescription());
            assertEquals(productDto.getPrice(), product.getPrice());
            assertEquals(productDto.getPrice(), product.getPrice());
            assertEquals(productDto.getManufacturerId(), product.getManufacturer().id());
        });
    }

    @Test
    void delete() {
        Assertions.assertTrue(productService.delete(PRODUCT_1));
        Assertions.assertFalse(productService.delete(-10));
    }
}