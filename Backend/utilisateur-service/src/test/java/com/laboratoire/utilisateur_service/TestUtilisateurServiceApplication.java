package com.laboratoire.utilisateur_service;

import org.springframework.boot.SpringApplication;

public class TestUtilisateurServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(UtilisateurServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
