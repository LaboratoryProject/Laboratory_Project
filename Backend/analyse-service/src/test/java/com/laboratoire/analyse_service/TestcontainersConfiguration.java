package com.laboratoire.analyse_service;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

	@Bean
	@ServiceConnection
	public PostgreSQLContainer<?> postgresContainer() {
		PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"))
				.withDatabaseName("analyse_service")
				.withUsername("admin")
				.withPassword("admin");
		postgres.start(); // Explicitly start the container
		return postgres;
	}

}
