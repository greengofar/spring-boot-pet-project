package com.andev.integration.http.controller;

import com.andev.integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static com.andev.dto.ProductCreateEditDto.Fields.amount;
import static com.andev.dto.ProductCreateEditDto.Fields.category;
import static com.andev.dto.ProductCreateEditDto.Fields.description;
import static com.andev.dto.ProductCreateEditDto.Fields.manufacturerId;
import static com.andev.dto.ProductCreateEditDto.Fields.model;
import static com.andev.dto.ProductCreateEditDto.Fields.name;
import static com.andev.dto.ProductCreateEditDto.Fields.price;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@AutoConfigureMockMvc
@RequiredArgsConstructor
class ProductControllerIT extends IntegrationTestBase {

    private final MockMvc mockMvc;

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("product/products"))
                .andExpect(model().attributeExists("products"));
    }

    @Test
    void findById() {
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/products")
                        .param(name, "test")
                        .param(category, "COMPUTERS")
                        .param(amount, "1")
                        .param(price, "1")
                        .param(description, "test description")
                        .param(manufacturerId, "1")
                        .param(model, "test model")
                )
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrlPattern("/products/{\\d+}")
                );
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}