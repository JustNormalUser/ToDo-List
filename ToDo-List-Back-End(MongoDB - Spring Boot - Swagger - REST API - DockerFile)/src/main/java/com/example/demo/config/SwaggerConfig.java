package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	
	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(metaInfo()) //apiInfo created as a method below.
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.example.demo"))//select from this package
				.paths(PathSelectors.any()) //with any end-point
				.build();
	}

	private ApiInfo metaInfo() { //Method for showing some descriptions about ToDo List

		return new ApiInfoBuilder().title("Spring Boot ToDo List with REST API")
				.description("This ToDo List includes MongoDB, Spring Boot, Swagger and REST API")
				.version("V1.0")
				.build();
	}
	
}
