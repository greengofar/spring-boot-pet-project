package com.andev.mapper;

import com.andev.dto.OrderReadDto;
import com.andev.dto.ProductReadDto;
import com.andev.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderReadMapper implements Mapper<Order, OrderReadDto> {

    private final ProductReadMapper productReadMapper;

    @Override
    public OrderReadDto map(Order object) {
        ProductReadDto productReadDto = Optional.ofNullable(object.getProduct())
                .map(productReadMapper::map)
                .orElse(null);

        return new OrderReadDto(
                object.getId(),
                object.getDateOrder(),
                object.getDateClosing(),
                productReadDto,
                object.getAmount(),
                object.getUserAddress(),
                object.getPayment(),
                object.getStatus(),
                object.getUser().getId()
        );
    }
}
