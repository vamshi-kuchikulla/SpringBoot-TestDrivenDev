package com.cst.tdd.util;

import com.cst.tdd.dto.ProductRequest;
import com.cst.tdd.dto.ProductResponse;
import com.cst.tdd.entity.Product;

public class ValueMapper {
    public static ProductResponse entityToDto(Product product){
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getDescription());
        productResponse.setPrice(product.getPrice());
        return productResponse;
    }

    public static Product dtoToEntity(ProductRequest productRequest){
        Product product = new Product();
        product.setId(productRequest.getId());
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        return product;

    }
}
