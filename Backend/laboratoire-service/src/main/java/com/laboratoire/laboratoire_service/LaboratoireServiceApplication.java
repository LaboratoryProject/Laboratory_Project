package com.laboratoire.laboratoire_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LaboratoireServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaboratoireServiceApplication.class, args);
	}

}

