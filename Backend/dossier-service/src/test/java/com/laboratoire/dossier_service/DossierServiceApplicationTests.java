package com.laboratoire.dossier_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class DossierServiceApplicationTests {

	@Test
	void contextLoads() {
		// Teste simplement si le contexte de Spring peut se charger sans erreurs
	}

}
