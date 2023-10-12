package com.andev.mapper;

import com.andev.dao.ProductRepository;
import com.andev.dao.UserRepository;
import com.andev.dto.OrderCreateEditDto;
import com.andev.entity.Order;
import com.andev.entity.Product;
import com.andev.entity.User;
import com.andev.entity.UserAddress;
import com.andev.entity.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderCreateEditMapper implements Mapper<OrderCreateEditDto, Order> {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public Order map(OrderCreateEditDto fromObject, Order toObject) {
        toObject.setDateOrder(fromObject.getDateOrder());
        toObject.setDateClosing(fromObject.getDateClosing());
        toObject.setAmount(fromObject.getAmount());
        toObject.setPayment(fromObject.getPayment());
        toObject.setStatus(fromObject.getStatus());
        toObject.setUserAddress(getAddress(fromObject));
        toObject.setUser(getUser(fromObject.getUsername()));
        toObject.setProduct(getProduct(fromObject.getProductId()));
        toObject.setDateClosing(fromObject.getDateClosing());
        return toObject;
    }

    @Override
    public Order map(OrderCreateEditDto object) {
        Order order = new Order();
        order.setDateOrder(LocalDate.now());
        order.setAmount(object.getAmount());
        order.setPayment(object.getPayment());
        order.setStatus(Status.NEW);
        order.setUserAddress(getAddress(object));
        order.setUser(getUser(object.getUsername()));
        order.setProduct(getProduct(object.getProductId()));
        return order;
    }

    /*private void copy(OrderCreateEditDto object, Order order) {
        order.setDateOrder(LocalDate.now());
        order.setAmount(object.getAmount());
        order.setPayment(object.getPayment());
        order.setStatus(Status.NEW);
        order.setUserAddress(getAddress(object));
        order.setUser(getUser(object.getUsername()));
        order.setProduct(getProduct(object.getProductId()));
        order.setDateClosing(object.getDateClosing());
    }*/

    private static UserAddress getAddress(OrderCreateEditDto object) {
        return UserAddress.builder()
                .town(object.getTown())
                .street(object.getStreet())
                .houseNumber(object.getHouseNumber())
                .apartmentNumber(object.getApartmentNumber())
                .postalCode(object.getPostalCode())
                .build();
    }

    private Product getProduct(Integer productId) {
        return Optional.ofNullable(productId)
                .flatMap(productRepository::findById)
                .orElseThrow();
    }

    private User getUser(String username) {
        return Optional.ofNullable(username)
                .flatMap(userRepository::findByUserName)
                .orElseThrow();
    }
}