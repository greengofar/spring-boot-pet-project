package com.andev.integration.dao;

import com.andev.dao.ProductRepository;
import com.andev.dto.ProductFilter;
import com.andev.entity.Product;
import com.andev.entity.enums.Category;
import com.andev.integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class ProductRepositoryIT extends IntegrationTestBase {

    private final ProductRepository repository;

    @Test
    void whenFindProductByQuerydslAndFilterOfManufacturerAndCategory_thenReturnListProducts() {
        ProductFilter filter = ProductFilter.builder()
                .manufacturerName("Samsung")
                .category(Category.ELECTRONICS)
                .build();
        List<Product> filtedProducts = repository.findProductByFilter_querydsl(filter);
        assertThat(filtedProducts.size()).isEqualTo(1);
        assertThat(filtedProducts.get(0).getModel()).isEqualTo("Galaxy S22");
    }

    @Test
    void whenFindProductByQuerydslAndFilterOfNameAndPrice_thenReturnListProducts() {
        ProductFilter filter = ProductFilter.builder()
                .productName("smartphone")
                .priceMin(BigDecimal.valueOf(740))
                .priceMax(BigDecimal.valueOf(900))
                .build();
        List<Product> filtedProducts = repository.findProductByFilter_querydsl(filter);
        assertThat(filtedProducts.size()).isEqualTo(2);

        List<String> models = filtedProducts.stream().map(Product::getModel).collect(Collectors.toList());
        assertThat(models).containsExactlyInAnyOrder("iPhone 11", "Galaxy S22");
    }

    @Test
    void whenFindProductByQuerydslAndEmptyFilter_thenReturnListOfAllProducts() {
        ProductFilter filterEmpty = ProductFilter.builder().build();
        List<Product> products = repository.findProductByFilter_querydsl(filterEmpty);
        assertThat(products.size()).isEqualTo(6);
    }

    @Test
    void whenFindProductByCriteriaAndFilterOfManufacturerAndCategory_thenReturnListProducts() {
        ProductFilter filter = ProductFilter.builder()
                .manufacturerName("Samsung")
                .category(Category.ELECTRONICS)
                .build();
        List<Product> filtedProducts = repository.findProductByFilter_criteria(filter);
        assertThat(filtedProducts.size()).isEqualTo(1);
        assertThat(filtedProducts.get(0).getModel()).isEqualTo("Galaxy S22");
    }

    @Test
    void whenFindProductByCriteriaAndFilterOfNameAndPrice_thenReturnListProducts() {
        ProductFilter filter = ProductFilter.builder()
                .productName("smartphone")
                .priceMin(BigDecimal.valueOf(740))
                .priceMax(BigDecimal.valueOf(900))
                .build();
        List<Product> filtedProducts = repository.findProductByFilter_criteria(filter);
        assertThat(filtedProducts.size()).isEqualTo(2);

        List<String> models = filtedProducts.stream().map(Product::getModel).collect(Collectors.toList());
        assertThat(models).containsExactlyInAnyOrder("iPhone 11", "Galaxy S22");
    }

    @Test
    void whenFindProductByCriteriaAndEmptyFilter_thenReturnListOfAllProducts() {
        ProductFilter filterEmpty = ProductFilter.builder().build();
        List<Product> products = repository.findProductByFilter_criteria(filterEmpty);
        assertThat(products.size()).isEqualTo(6);
    }
}