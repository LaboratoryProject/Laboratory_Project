package com.laboratoire.dossier_service.controller;

import com.laboratoire.dossier_service.model.Dossier;
import com.laboratoire.dossier_service.service.DossierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")  // Autorise les requêtes depuis Angular
@RestController
@RequestMapping("/api/dossier")
public class DossierController {

    @Autowired
    private DossierService dossierService;

    // Ajouter un nouveau dossier
    @PostMapping
    public ResponseEntity<Dossier> createDossier(@RequestBody Dossier dossier) {
        Dossier createdDossier = dossierService.saveDossier(dossier);
        return new ResponseEntity<>(createdDossier, HttpStatus.CREATED);
    }


    // Récupérer tous les dossiers
    @GetMapping
    public ResponseEntity<List<Dossier>> getAllDossiers() {
        List<Dossier> dossiers = dossierService.getAllDossiers();
        return ResponseEntity.ok(dossiers);
    }

    // Récupérer un dossier par son numéro (numDossier)
    @GetMapping("/{numDossier}")
    public ResponseEntity<Dossier> getDossierByNum(@PathVariable Long numDossier) {
        return dossierService.getDossierByNum(numDossier)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Mise à jour d'un dossier
    @PutMapping("/{numDossier}")
    public ResponseEntity<Dossier> updateDossier(@PathVariable Long numDossier, @RequestBody Dossier updatedDossier) {
        if (updatedDossier.getFkEmailUtilisateur() == null || updatedDossier.getFkEmailUtilisateur().isEmpty()) {
            return ResponseEntity.badRequest().build(); // Return 400 if fkEmailUtilisateur is missing
        }

        try {
            Dossier updated = dossierService.updateDossier(numDossier, updatedDossier);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // Return 404 if Dossier not found
        }
    }


    // Supprimer un dossier
    @DeleteMapping("/{numDossier}")
    public ResponseEntity<Void> deleteDossier(@PathVariable Long numDossier) {
        dossierService.deleteDossier(numDossier);
        return ResponseEntity.noContent().build(); // Status 204 for successful deletion
    }

    // Exemple d'endpoint pour récupérer les analyses
    @GetMapping("/analyses")
    public ResponseEntity<List<String>> getAnalyses() {
        List<String> analyses = dossierService.getAnalyses();  // Remplacez par la logique réelle de récupération
        return ResponseEntity.ok(analyses);
    }
}
