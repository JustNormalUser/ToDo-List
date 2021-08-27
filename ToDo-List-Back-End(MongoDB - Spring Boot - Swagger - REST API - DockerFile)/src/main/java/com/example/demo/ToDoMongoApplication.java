package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2//Activate Swagger2
public class ToDoMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToDoMongoApplication.class, args);
	}

}
