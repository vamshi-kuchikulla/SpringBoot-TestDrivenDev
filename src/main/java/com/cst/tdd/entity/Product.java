package com.cst.tdd.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "products_tbl")
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
}
