package com.laboratoire.laboratoire_service;

import org.springframework.boot.SpringApplication;

public class TestLaboratoireServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(LaboratoireServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
