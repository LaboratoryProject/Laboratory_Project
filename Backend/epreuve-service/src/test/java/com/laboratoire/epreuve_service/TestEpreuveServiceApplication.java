package com.laboratoire.epreuve_service;

import org.springframework.boot.SpringApplication;

public class TestEpreuveServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(EpreuveServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
