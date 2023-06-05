package com.andev.mapper;

import com.andev.dto.ManufacturerReadDto;
import com.andev.entity.Manufacturer;
import org.springframework.stereotype.Component;

@Component
public class ManufacturerReadMapper implements Mapper<Manufacturer, ManufacturerReadDto> {


    @Override
    public ManufacturerReadDto map(Manufacturer object) {
        return new ManufacturerReadDto(
                object.getId(),
                object.getName(),
                object.getDescription());
    }
}
