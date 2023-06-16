package com.andev.service;

import com.andev.dao.ProductRepository;
import com.andev.dao.QPredicate;
import com.andev.dto.ProductCreateEditDto;
import com.andev.dto.ProductFilter;
import com.andev.dto.ProductReadDto;
import com.andev.entity.Product;
import com.andev.mapper.ProductCreateEditeMapper;
import com.andev.mapper.ProductReadMapper;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static com.andev.entity.QManufacturer.manufacturer;
import static com.andev.entity.QProduct.product;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductReadMapper productReadMapper;
    private final ProductCreateEditeMapper productCreateEditeMapper;
    private final ProductImageService productImageService;

    public Page<ProductReadDto> findAll(ProductFilter filter, Pageable pageable) {
        Predicate predicate = QPredicate.builder()
                .add(filter.getProductName(), product.name::containsIgnoreCase)
                .add(filter.getManufacturerName(), manufacturer.name::containsIgnoreCase)
                .add(filter.getPriceMin(), product.price::gt)
                .add(filter.getPriceMax(), product.price::lt)
                .add(filter.getCategory(), product.category::eq)
                .buildAnd();
        return productRepository.findAll(predicate, pageable)
                .map(productReadMapper::map);
    }

    public List<ProductReadDto> findAll() {
        return productRepository.findAll().stream()
                .map(productReadMapper::map)
                .toList();
    }

    public Optional<ProductReadDto> findById(Integer id) {
        return productRepository.findById(id)
                .map(productReadMapper::map);
    }

    public Optional<byte[]> findImage(Integer id){
        return productRepository.findById(id)
                .map(Product::getImageName)
                .filter(StringUtils::hasText)
                .flatMap(productImageService::getImage);
    }

    @Transactional
    public ProductReadDto create(ProductCreateEditDto productDto) {
        return Optional.of(productDto)
                .map(dto -> {
                    uploadImage(dto.getImage());
                    return productCreateEditeMapper.map(dto);
                })
                .map(productRepository::save)
                .map(productReadMapper::map)
                .orElseThrow();
    }

    @SneakyThrows
    private void uploadImage(MultipartFile image) {
        if (!image.isEmpty())
            productImageService.uploadImage(image.getOriginalFilename(), image.getInputStream());
    }

    @Transactional
    public Optional<ProductReadDto> update(Integer id, ProductCreateEditDto productDto) {
        return productRepository.findById(id)
                .map(entity -> {
                    uploadImage(productDto.getImage());
                    return productCreateEditeMapper.map(productDto, entity);
                })
                .map(productRepository::saveAndFlush)
                .map(productReadMapper::map);
    }

    @Transactional
    public boolean delete(Integer id) {
        return productRepository.findById(id)
                .map(entity -> {
                    productRepository.delete(entity);
                    productRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
