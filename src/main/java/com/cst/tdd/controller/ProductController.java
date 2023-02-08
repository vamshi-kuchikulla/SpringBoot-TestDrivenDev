package com.cst.tdd.controller;

import com.cst.tdd.dto.ProductRequest;
import com.cst.tdd.dto.ProductResponse;
import com.cst.tdd.exception.NoSuchProductFoundException;
import com.cst.tdd.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @GetMapping
    public String getMessage() {
        return "Spring Boot Product service Api!!!";
    }


    @Operation(summary = "This is to add the Product in Database")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Details saved the Database",
            content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Page not found", content = @Content)})
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest) {
        return productService.createProduct(productRequest);
    }


    @Operation(summary = "This is to fetch All the Products stored in Db")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Details of All the Products",
            content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Page not found", content = @Content)})
    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }


    @Operation(summary = "This is to fetch the Product by Id stored in Db")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Details of the Product By Id",
            content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Page not found", content = @Content)})
    @GetMapping("getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") String id) throws NoSuchProductFoundException {
        ProductResponse productById = productService.getProductById(id);
        if (productById != null) {
            return new ResponseEntity<>(productById, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "This is to update the Product by Id stored in Db")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "update the Product By Id",
            content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Page not found", content = @Content)})
    @PutMapping("update/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @RequestBody ProductRequest productRequest, @PathVariable("id") String id) throws NoSuchProductFoundException {
        ProductResponse existingProduct = productService.getProductById(id);
        if (existingProduct != null) {
            ProductResponse response = productService.createProduct(productRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "This is to delete the Product by Id stored in Db")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "delete the Product By Id",
            content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Page not found", content = @Content)})
    @DeleteMapping("/deleteById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteById(@PathVariable("id") String id) {
        productService.deleteById(id);
        return new ResponseEntity<>("Product deleted successfully !!!!", HttpStatus.OK);
    }
}
