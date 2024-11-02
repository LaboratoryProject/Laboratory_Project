package com.laboratoire.epreuve_service.controller;

import com.laboratoire.epreuve_service.model.Epreuve;
import com.laboratoire.epreuve_service.service.EpreuveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/epreuves")
public class EpreuveController {

    private final EpreuveService epreuveService;

    @Autowired
    public EpreuveController(EpreuveService epreuveService) {
        this.epreuveService = epreuveService;
    }

    @GetMapping
    public List<Epreuve> getAllEpreuves() {
        return epreuveService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Epreuve> getEpreuveById(@PathVariable Long id) {
        return epreuveService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/analyse/{analyseId}")
    public List<Epreuve> getEpreuvesByAnalyse(@PathVariable Long analyseId) {
        return epreuveService.findByAnalyse(analyseId);
    }

    @PostMapping
    public Epreuve createEpreuve(@RequestBody Epreuve epreuve) {
        return epreuveService.save(epreuve);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Epreuve> updateEpreuve(@PathVariable Long id, @RequestBody Epreuve epreuve) {
        if (!epreuveService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        epreuve.setId(id);
        return ResponseEntity.ok(epreuveService.save(epreuve));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEpreuve(@PathVariable Long id) {
        if (!epreuveService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        epreuveService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<Epreuve> searchEpreuves(@RequestParam String nom) {
        return epreuveService.searchByNom(nom);
    }
}