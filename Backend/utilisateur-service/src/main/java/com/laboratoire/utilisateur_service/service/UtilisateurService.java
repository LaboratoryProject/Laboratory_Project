package com.laboratoire.utilisateur_service.service;

import com.laboratoire.utilisateur_service.model.Role;
import com.laboratoire.utilisateur_service.model.Utilisateur;

import java.util.List;

public interface UtilisateurService {
    List<Utilisateur> getUtilisateursByRole(Role role);
    List<Utilisateur> getUtilisateursByLaboratoire(Long laboratoireId);
    void deleteUtilisateur(Long id);
    Utilisateur updateUtilisateur(Long id, Utilisateur utilisateurDetails);
    Utilisateur createUtilisateur(Utilisateur utilisateur);
    Utilisateur getUtilisateurById(Long id);
    List<Utilisateur> getAllUtilisateurs();

}
