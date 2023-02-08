package com.cst.tdd.service;

import com.cst.tdd.dto.ProductRequest;
import com.cst.tdd.dto.ProductResponse;
import com.cst.tdd.entity.Product;
import com.cst.tdd.exception.NoSuchProductFoundException;
import com.cst.tdd.repository.ProductRepository;
import com.cst.tdd.util.ValueMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public ProductResponse createProduct(ProductRequest productRequest) {
         Product product = ValueMapper.dtoToEntity(productRequest);
        return ValueMapper.entityToDto(productRepository.save(product));
    }
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(ValueMapper::entityToDto).collect(Collectors.toList());
    }
    public ProductResponse getProductById(String id) throws NoSuchProductFoundException {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
           Product savedProduct = product.get();
            return ValueMapper.entityToDto(savedProduct);
        }else {
            throw new NoSuchProductFoundException("No Product with given Id in Data"+ id);
        }
    }
    public void deleteById(String id) {
        productRepository.deleteById(id);
    }

}
