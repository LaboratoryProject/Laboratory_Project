package com.laboratoire.dossier_service.service;

import com.laboratoire.dossier_service.model.Dossier;
import com.laboratoire.dossier_service.repository.DossierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DossierService {

    @Autowired
    private DossierRepository dossierRepository;

    // Ajouter un nouveau dossier
    public Dossier addDossier(Dossier dossier) {
        if (dossier.getFkEmailUtilisateur() == null || dossier.getFkEmailUtilisateur().isEmpty()) {
            throw new IllegalArgumentException("Email utilisateur is required"); // Throw error if missing
        }
        return dossierRepository.save(dossier);
    }

    public Dossier saveDossier(Dossier dossier) {
        return dossierRepository.save(dossier);
    }


    // Récupérer tous les dossiers
    public List<Dossier> getAllDossiers() {
        return dossierRepository.findAll();
    }

    // Récupérer un dossier par son numéro
    public Optional<Dossier> getDossierByNum(Long numDossier) {
        return dossierRepository.findByNumDossier(numDossier);
    }

    // Mise à jour d'un dossier
    public Dossier updateDossier(Long numDossier, Dossier updatedDossier) {
        return dossierRepository.findByNumDossier(numDossier).map(dossier -> {
            if (updatedDossier.getFkEmailUtilisateur() == null || updatedDossier.getFkEmailUtilisateur().isEmpty()) {
                throw new IllegalArgumentException("Email utilisateur is required"); // Throw error if missing
            }

            dossier.setFkEmailUtilisateur(updatedDossier.getFkEmailUtilisateur());
            dossier.setFkIdPatient(updatedDossier.getFkIdPatient());
            dossier.setDate(updatedDossier.getDate());
            return dossierRepository.save(dossier);
        }).orElseThrow(() -> new RuntimeException("Dossier non trouvé pour le numDossier : " + numDossier));
    }


    // Supprimer un dossier
    public void deleteDossier(Long numDossier) {
        dossierRepository.deleteById(numDossier);
    }

    // Récupérer des analyses (exemple)
    public List<String> getAnalyses() {
        return List.of("Analyse 1", "Analyse 2", "Analyse 3");  // Remplacez par la logique réelle
    }
}
