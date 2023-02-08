package com.cst.tdd;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Product Service Api"))
public class TddTestApplication {
	public static void main(String[] args) {
		SpringApplication.run(TddTestApplication.class, args);
	}
}
