package com.laboratoire.dossier_service;

import com.laboratoire.dossier_service.controller.DossierController;
import com.laboratoire.dossier_service.model.Dossier;
import com.laboratoire.dossier_service.service.DossierService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Initialisation des mocks
public class DossierControllerTest {

    @Mock
    private DossierService dossierService;

    @InjectMocks
    private DossierController dossierController;

    @Test
    public void testCreateDossier() {
        // Arrange : Préparation des données et comportement simulé
        Dossier dossier = new Dossier();
        dossier.setFkEmailUtilisateur("test@example.com");
        dossier.setFkIdPatient(1L);
        dossier.setDate(LocalDate.now());

        when(dossierService.addDossier(any(Dossier.class))).thenReturn(dossier);

        // Act : Appel de la méthode du contrôleur
        Dossier result = dossierController.createDossier(dossier).getBody();

        // Assert : Vérification du résultat
        assertEquals("test@example.com", result.getFkEmailUtilisateur());
        assertEquals(1L, result.getFkIdPatient());
        verify(dossierService, times(1)).addDossier(any(Dossier.class)); // Vérifie que le service a bien été appelé
    }
}
