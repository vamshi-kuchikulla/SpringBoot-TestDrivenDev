package com.cst.tdd.java8;

import lombok.Builder;
import lombok.Data;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
@Builder
class Product {
    private int id;
    private String name;
}

public class Java8Demo {
    public static void main(String[] args) {
        List<Product> productList = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> Product.builder()
                        .id(new Random().nextInt(100))
                        .name("product " + i).build()).collect(Collectors.toList());
        productList.stream().filter(product -> product.getId() < 50).forEach(System.out::println);
        System.out.println("***************");
        productList.stream()
                .sorted(Comparator.comparing(product -> product.equals(product.getId())))
                .forEach(System.out::println);
    }
}
