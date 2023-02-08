package com.cst.tdd.controller;

import com.cst.tdd.dto.ProductRequest;
import com.cst.tdd.dto.ProductResponse;
import com.cst.tdd.entity.Product;
import com.cst.tdd.repository.ProductRepository;
import com.cst.tdd.service.ProductService;
import com.cst.tdd.util.ValueMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.CoreMatchers.is;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class ProductControllerTest {

    @MockBean
    private ProductRepository productRepository;
    @MockBean
    private ProductService productService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldCreatedProduct() throws Exception {
        when(productService.createProduct(any())).thenReturn(ValueMapper.entityToDto(getProduct()));
        String productRequestString = objectMapper.writeValueAsString(getProduct());
        mockMvc.perform(MockMvcRequestBuilders.post("/api/product/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productRequestString))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.id", is(getProduct().getId())));
    }

    @Test
    void getAllProductsTest() throws Exception {
        when(productService.getAllProducts()).thenReturn(getProducts());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/product/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(getProducts().size())));
    }

    @Test
    void getProductByIdTest() throws Exception {
        String id = "1";
        when(productService.getProductById(id)).thenReturn(ValueMapper.entityToDto(getProduct()));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/product/getById/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(getProduct().getName())));
    }

    @Test
    void updateProductTest() throws Exception {
        String id = "1";
        when(productService.getProductById(id)).thenReturn(getExistingProduct());
        when(productService.createProduct(any(ProductRequest.class))).thenReturn(getUpdatedProduct());
        String productRequestString = objectMapper.writeValueAsString(getProductRequest());
        mockMvc.perform(MockMvcRequestBuilders.put("/api/product/update/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productRequestString))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteByITest() throws Exception {
        String id = "1";
        willDoNothing().given(productService).deleteById(id);
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.delete("/api/product/deleteById/{id}", id));
        response.andExpect(status().isOk())
                .andDo(print());
    }


    private Product getProduct() {
        return Product.builder().id("1").name("iPhone 15").description("iPhone 15")
                .price(BigDecimal.valueOf(12000)).build();
    }

    private ProductRequest getProductRequest() {
        return ProductRequest.builder().name("iPhone 13").description("iPhone 13")
                .price(BigDecimal.valueOf(16000)).build();
    }

    private ProductResponse getExistingProduct() {
        return ProductResponse.builder().id("1").name("iPhone 13").description("iPhone 13")
                .price(BigDecimal.valueOf(16000)).build();
    }

    private ProductResponse getUpdatedProduct() {
        return ProductResponse.builder().id("1").name("Apple iPhone 14").description("iPhone 14 Pro 256gb")
                .price(BigDecimal.valueOf(21000)).build();
    }

    private List<ProductResponse> getProducts() {
        return Arrays.asList(
                new ProductResponse("1", "Phone", "Apple iPhone 15", BigDecimal.valueOf(12000)),
                new ProductResponse("2", "Laptop", "Dell xps Laptop", BigDecimal.valueOf(21000)));
    }

}