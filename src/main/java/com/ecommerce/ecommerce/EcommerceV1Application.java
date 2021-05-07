package com.ecommerce.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableR2dbcRepositories
public class EcommerceV1Application {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceV1Application.class, args);
	}

}
