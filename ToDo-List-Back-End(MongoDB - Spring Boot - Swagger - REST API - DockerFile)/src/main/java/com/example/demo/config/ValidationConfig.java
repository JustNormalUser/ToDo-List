package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration //Shows this class has one or more @Bean declaration.
public class ValidationConfig {

	@Bean //It is an object which calls custom early declared method(s).
	public ValidatingMongoEventListener ValidationMongoEventListener() { //Control the variables before they saved in a database. 
		//Also it senses null value. Call constraintviolationexception.
		return new ValidatingMongoEventListener(validator());
	}

	@Bean //It is an object which calls custom early declared method(s).
	public LocalValidatorFactoryBean validator() { //Hibernate and add much more interfaces. Also, JSR-303 for BeanValidation. 
		//It checks the beans have the right value of them.
		return new LocalValidatorFactoryBean();
	}
}
