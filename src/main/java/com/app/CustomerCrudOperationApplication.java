package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Spring Security User API", version = "1.0", description = "User Authentication & Authorization Spring Boot Jwt token"))
@SecurityScheme(name = "JWT-Token", scheme = "bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class CustomerCrudOperationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerCrudOperationApplication.class, args);
		System.out.println("Customer crud operation");
	}
}
