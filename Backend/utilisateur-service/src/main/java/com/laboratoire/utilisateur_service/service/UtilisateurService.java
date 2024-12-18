package com.laboratoire.utilisateur_service.service;

import com.laboratoire.utilisateur_service.controller.ApiResponse;
import com.laboratoire.utilisateur_service.dto.AdminUtilisateurDTO;
import com.laboratoire.utilisateur_service.dto.UtilisateurDTO;
import com.laboratoire.utilisateur_service.model.Role;
import com.laboratoire.utilisateur_service.model.Utilisateur;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.List;

public interface UtilisateurService {
    List<Utilisateur> getUtilisateursByRole(Role role);
    List<Utilisateur> getUtilisateursByLaboratoire(Long laboratoireId);
    ApiResponse deleteUtilisateur(Long id);
    ApiResponse updateUtilisateur(Long id, Utilisateur utilisateurDetails);
    ApiResponse createUtilisateur(UtilisateurDTO dto) throws IOException;

    Utilisateur getUtilisateurById(Long id);
    List<Utilisateur> getAllUtilisateurs();
    List<AdminUtilisateurDTO> getAllAdminUtilisateurs();
    void supprimerAdministrateur(Long id);
    Utilisateur modifierAdministrateur(Long id, Utilisateur utilisateur);
    Utilisateur modifyUtilisateur(Utilisateur utilisateur);
    Utilisateur getUserByKeycloakId(String keycloakId);

}
