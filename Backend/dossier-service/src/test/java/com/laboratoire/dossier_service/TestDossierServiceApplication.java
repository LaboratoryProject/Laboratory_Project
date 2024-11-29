package com.laboratoire.dossier_service;

import org.springframework.boot.SpringApplication;

public class TestDossierServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(DossierServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
