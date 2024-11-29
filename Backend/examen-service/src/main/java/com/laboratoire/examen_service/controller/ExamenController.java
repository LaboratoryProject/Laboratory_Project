package com.laboratoire.examen_service.controller;

import com.laboratoire.examen_service.model.Examen;
import com.laboratoire.examen_service.service.ExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/examen")
public class ExamenController {

    @Autowired
    private ExamenService examenService;

    // Ajouter un examen
    @PostMapping
    public ResponseEntity<Examen> createExamen(@RequestBody Examen examen) {
        Examen savedExamen = examenService.addExamen(examen);
        return ResponseEntity.ok(savedExamen);
    }

    // Récupérer tous les examens
    @GetMapping
    public ResponseEntity<List<Examen>> getAllExamen() {
        List<Examen> examens = examenService.getAllExamen();
        return ResponseEntity.ok(examens);
    }

    // Récupérer un examen par ID
    @GetMapping("/{id}")
    public ResponseEntity<Examen> getExamenById(@PathVariable Long id) {
        return examenService.getExamenById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Mettre à jour un examen
    @PutMapping("/{id}")
    public ResponseEntity<Examen> updateExamen(@PathVariable Long id, @RequestBody Examen updatedExamen) {
        try {
            Examen updated = examenService.updateExamen(id, updatedExamen);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Supprimer un examen
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExamen(@PathVariable Long id) {
        examenService.deleteExamen(id);
        return ResponseEntity.noContent().build();
    }
}
