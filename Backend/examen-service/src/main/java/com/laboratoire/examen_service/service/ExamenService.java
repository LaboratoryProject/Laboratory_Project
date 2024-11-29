package com.laboratoire.examen_service.service;

import com.laboratoire.examen_service.model.Examen;
import com.laboratoire.examen_service.repository.ExamenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamenService {

    @Autowired
    private ExamenRepository examenRepository;

    // Ajouter un examen
    public Examen addExamen(Examen examen) {
        return examenRepository.save(examen);
    }

    // Récupérer tous les examens
    public List<Examen> getAllExamen() {
        return examenRepository.findAll();
    }

    // Récupérer un examen par ID
    public Optional<Examen> getExamenById(Long id) {
        return examenRepository.findById(id);
    }

    // Mettre à jour un examen
    public Examen updateExamen(Long id, Examen updatedExamen) {
        return examenRepository.findById(id).map(examen -> {
            examen.setFkNumDossier(updatedExamen.getFkNumDossier());
            examen.setFkIdEpreuve(updatedExamen.getFkIdEpreuve());
            examen.setFkIdTestAnalyse(updatedExamen.getFkIdTestAnalyse());
            examen.setResultat(updatedExamen.getResultat());
            return examenRepository.save(examen);
        }).orElseThrow(() -> new RuntimeException("Examen non trouvé pour l'id : " + id));
    }

    // Supprimer un examen
    public void deleteExamen(Long id) {
        examenRepository.deleteById(id);
    }
}
