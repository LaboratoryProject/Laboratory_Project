package com.laboratoire.contactLabo_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ContactLaboServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactLaboServiceApplication.class, args);
	}

}
