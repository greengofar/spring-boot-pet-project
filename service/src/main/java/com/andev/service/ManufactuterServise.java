package com.andev.service;

import com.andev.dao.ManufacturerRepository;
import com.andev.dto.ManufacturerReadDto;
import com.andev.mapper.ManufacturerReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ManufactuterServise {

    private final ManufacturerRepository manufacturerRepository;
    private final ManufacturerReadMapper manufacturerReadMapper;

    public List<ManufacturerReadDto> findAll(){
        return manufacturerRepository.findAll().stream()
                .map(manufacturerReadMapper::map)
                .toList();
    }
}
