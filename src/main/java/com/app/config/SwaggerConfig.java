//package com.app.config;
//
//import java.util.Arrays;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.ApiKey;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//
//@Configuration
//public class SwaggerConfig {
//
//	public static final String AUTHORIZATION_HEADER = "Authorization";
//	
//	private ApiKey apiKeys() {
//		return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
//	}
//	
//	@Bean
//	public Docket api() {
//		return new Docket(DocumentationType.SWAGGER_2)
//				.apiInfo(getInfo())
//				.securityContexts(null)
//				.securitySchemes(Arrays.asList(apiKeys()))
//				.select().apis(RequestHandlerSelectors.any())
//				.paths(PathSelectors.any()).build();
//	}
//
//	private ApiInfo getInfo() {
//		return new ApiInfo("Authentication User with Spring Boot Security",
//				"This project is develop for user authentication", "1.0", "Term of login service",
//				new Contact("Javaisawesome", "https://www.javaisawesome.com/", "javaisawesome@gmail.com"), "Licence of Api",
//				"Api Licence Url", java.util.Collections.emptyList());
//
//	}
//
//} 
