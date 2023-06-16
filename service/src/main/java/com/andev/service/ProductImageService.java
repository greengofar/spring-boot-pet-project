package com.andev.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

@Service
@RequiredArgsConstructor
public class ProductImageService {

    @Value(value = "${app.product.image.bucket}")
    private final String bucket;

    @SneakyThrows
    public void uploadImage(String imageName, InputStream content){
        Path fullImagePath = Path.of(bucket, imageName);

        try(content){
            Files.createDirectories(fullImagePath.getParent());
            Files.write(fullImagePath,content.readAllBytes(), CREATE, TRUNCATE_EXISTING);
        }
    }

    @SneakyThrows
    public Optional<byte[]> getImage(String imageName){
        Path fullImagePath = Path.of(bucket, imageName);

        return Files.exists(fullImagePath)
                ? Optional.of(Files.readAllBytes(fullImagePath))
                : Optional.empty();
    }
}
