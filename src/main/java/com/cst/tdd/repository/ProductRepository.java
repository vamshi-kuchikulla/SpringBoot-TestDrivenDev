package com.cst.tdd.repository;

import com.cst.tdd.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProductRepository extends JpaRepository<Product, String> {
}
