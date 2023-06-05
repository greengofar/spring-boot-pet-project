package com.andev.dao;

import com.andev.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer> {

    Optional<Manufacturer> findByName(String name);

    List<Manufacturer> findAllByNameContainingIgnoreCase(String fragment);
}
