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
        return dossierRepository.save(dossier);
    }

    // Récupérer tous les dossiers
    public List<Dossier> getAllDossiers() {
        return dossierRepository.findAll();
    }

    // Récupérer un dossier par ID
    public Optional<Dossier> getDossierById(Long id) {
        return dossierRepository.findById(id);
    }

    // Mettre à jour un dossier
    public Dossier updateDossier(Long id, Dossier updatedDossier) {
        return dossierRepository.findById(id).map(dossier -> {
            dossier.setNumDossier(updatedDossier.getNumDossier());
            dossier.setFkEmailUtilisateur(updatedDossier.getFkEmailUtilisateur());
            dossier.setFkIdPatient(updatedDossier.getFkIdPatient());
            dossier.setDate(updatedDossier.getDate());
            return dossierRepository.save(dossier);
        }).orElseThrow(() -> new RuntimeException("Dossier non trouvé pour l'id : " + id));
    }

    // Supprimer un dossier
    public void deleteDossier(Long id) {
        dossierRepository.deleteById(id);
    }

    // Récupérer un dossier par son numéro
    public Optional<Dossier> getDossierByNum(String numDossier) {
        return dossierRepository.findByNumDossier(numDossier);
    }
}
