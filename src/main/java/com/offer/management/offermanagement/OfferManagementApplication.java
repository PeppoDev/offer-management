package com.offer.management.offermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "offer managment", version = "1"))
public class OfferManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(OfferManagementApplication.class, args);
	}

}
