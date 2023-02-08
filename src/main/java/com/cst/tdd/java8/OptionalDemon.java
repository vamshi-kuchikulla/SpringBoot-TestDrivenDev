package com.cst.tdd.java8;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Customer {
private int Id;
private String name;
private String email;
private List<String> mobileNumber;

}
public class OptionalDemon {
    public static void main(String[] args) {
        Customer customer = Customer.builder()
                .Id(101)
                .name("kvk")
                .email("k@vmail.com")
                .mobileNumber(Arrays.asList("8142621234","9704978182")).build();

        Optional<Object> emptyOptional = Optional.empty();
        System.out.println(emptyOptional);

        Optional<String> email = Optional.of(customer.getEmail());
        System.out.println(email);

        Optional<String> emailOf = Optional.ofNullable(customer.getEmail());
        System.out.println(emailOf);



    }
}
