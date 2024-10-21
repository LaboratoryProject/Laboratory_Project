package com.laboratoire.laboratoire_registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class LaboratoireRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaboratoireRegistryApplication.class, args);
	}

}
