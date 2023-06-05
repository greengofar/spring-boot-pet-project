package com.andev.integration.dao;

import com.andev.dao.ManufacturerRepository;
import com.andev.entity.Manufacturer;
import com.andev.integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

@RequiredArgsConstructor
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class ManufacturerRepositoryTest extends IntegrationTestBase {

    private final ManufacturerRepository manufacturerRepository;

    @Test
    void findByName() {
        manufacturerRepository.findByName("Bosch");
    }

    @Test
    void findAllByNameContainingIgnoreCase() {
        List<Manufacturer> result = manufacturerRepository.findAllByNameContainingIgnoreCase("a");
        Assertions.assertThat(result).hasSize(2);
    }
}