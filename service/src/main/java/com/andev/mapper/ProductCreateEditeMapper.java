package com.andev.mapper;

import com.andev.dao.ManufacturerRepository;
import com.andev.dto.ProductCreateEditDto;
import com.andev.entity.Manufacturer;
import com.andev.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static java.util.function.Predicate.not;

@Component
@RequiredArgsConstructor
public class ProductCreateEditeMapper implements Mapper<ProductCreateEditDto, Product> {

    private final ManufacturerRepository manufacturerRepository;

    @Override
    public Product map(ProductCreateEditDto fromObject, Product toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    @Override
    public Product map(ProductCreateEditDto object) {
        Product product = new Product();
        copy(object, product);
        return product;
    }

    private void copy(ProductCreateEditDto object, Product product) {
        product.setName(object.getName());
        product.setModel(object.getModel());
        product.setCategory(object.getCategory());
        product.setDescription(object.getDescription());
        product.setPrice(object.getPrice());
        product.setAmount(object.getAmount());
        product.setManufacturer(getManufacturer(object.getManufacturerId()));

        Optional.ofNullable(object.getImage())
                .filter(not(MultipartFile::isEmpty))
                .ifPresent(image -> product.setImageName(image.getOriginalFilename()));
    }

    private Manufacturer getManufacturer(Integer ManufacturerId) {
        return Optional.ofNullable(ManufacturerId)
                .flatMap(manufacturerRepository::findById)
                .orElse(null);
    }
}

