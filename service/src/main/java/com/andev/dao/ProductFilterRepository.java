package com.andev.dao;

import com.andev.dto.ProductFilter;
import com.andev.entity.Product;

import java.util.List;

public interface ProductFilterRepository {

    List<Product> findProductByFilter_querydsl(ProductFilter filter);

    List<Product> findProductByFilter_criteria(ProductFilter filter);
}
