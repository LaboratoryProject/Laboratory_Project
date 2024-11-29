package com.laboratoire.dossier_service.controller;

import com.laboratoire.dossier_service.model.Dossier;
import com.laboratoire.dossier_service.service.DossierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dossier")
public class DossierController {

    @Autowired
    private DossierService dossierService;

    // Ajouter un nouveau dossier
    @PostMapping
    public ResponseEntity<Dossier> createDossier(@RequestBody Dossier dossier) {
        Dossier savedDossier = dossierService.addDossier(dossier);
        return ResponseEntity.ok(savedDossier);
    }

    // Récupérer tous les dossiers
    @GetMapping
    public ResponseEntity<List<Dossier>> getAllDossiers() {
        List<Dossier> dossiers = dossierService.getAllDossiers();
        return ResponseEntity.ok(dossiers);
    }

    // Récupérer un dossier par son numéro
    @GetMapping("/{numDossier}")
    public ResponseEntity<Dossier> getDossierByNum(@PathVariable String numDossier) {
        return dossierService.getDossierByNum(numDossier)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Mettre à jour un dossier
    @PutMapping("/{numDossier}")
    public ResponseEntity<Dossier> updateDossier(@PathVariable String numDossier, @RequestBody Dossier updatedDossier) {
        try {
            Dossier updated = dossierService.updateDossier(numDossier, updatedDossier);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Supprimer un dossier
    @DeleteMapping("/{numDossier}")
    public ResponseEntity<Void> deleteDossier(@PathVariable String numDossier) {
        dossierService.deleteDossier(numDossier);
        return ResponseEntity.noContent().build();
    }
}
