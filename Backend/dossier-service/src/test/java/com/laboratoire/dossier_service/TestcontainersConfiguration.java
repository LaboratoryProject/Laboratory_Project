package com.laboratoire.dossier_service;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

	@Bean
	@ServiceConnection
	PostgreSQLContainer<?> postgresContainer() {
		// Démarre explicitement le conteneur PostgreSQL
		PostgreSQLContainer<?> container = new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"));
		container.start();
		// Affiche l'URL de la base de données dans les logs pour vérifier la connexion
		System.out.println("PostgreSQL container started at: " + container.getJdbcUrl());
		return container;
	}

}
