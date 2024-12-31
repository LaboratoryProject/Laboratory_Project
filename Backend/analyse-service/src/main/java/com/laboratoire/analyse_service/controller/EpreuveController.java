package com.laboratoire.analyse_service.controller;

import com.laboratoire.analyse_service.model.Epreuve;
import com.laboratoire.analyse_service.model.EpreuveDetailsDTO;
import com.laboratoire.analyse_service.service.EpreuveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/an/epreuves")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EpreuveController {

    private final EpreuveService epreuveService;

    @Autowired
    public EpreuveController(EpreuveService epreuveService) {
        this.epreuveService = epreuveService;
    }

    @PostMapping("/{testAnalyseId}")
    public ResponseEntity<Epreuve> ajouterEpreuve(@PathVariable int testAnalyseId, @RequestBody Epreuve epreuve) {
        try {
            Epreuve nouvelleEpreuve = epreuveService.ajouterEpreuve(epreuve, testAnalyseId);
            return ResponseEntity.status(201).body(nouvelleEpreuve);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Epreuve>> getAllEpreuves() {
        List<Epreuve> epreuves = epreuveService.getAllEpreuves();
        return ResponseEntity.ok(epreuves);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Epreuve> getEpreuveById(@PathVariable int id) {
        return epreuveService.getEpreuveById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerEpreuve(@PathVariable int id) {
        if (epreuveService.supprimerEpreuve(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<EpreuveDetailsDTO> getEpreuveDetails(@PathVariable int id) {
        return epreuveService.getEpreuveDetails(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
