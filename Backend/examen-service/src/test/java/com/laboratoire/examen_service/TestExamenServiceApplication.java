package com.laboratoire.examen_service;

import org.springframework.boot.SpringApplication;

public class TestExamenServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(ExamenServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
