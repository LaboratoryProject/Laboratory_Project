package com.laboratoire.analyse_service.controller;

import com.laboratoire.analyse_service.service.AnalyseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.laboratoire.analyse_service.model.Analyse;

import java.util.List;
@RestController
@RequestMapping("/api/analyse")
public class AnalyseController {

    private final AnalyseService analyseService;
    //  private final EmailService emailService;  // Inject EmailService

    @Autowired
    public AnalyseController(AnalyseService analyseService) {
        this.analyseService = analyseService;

    }

    @PostMapping
    public ResponseEntity<Analyse> createAnalyse(@RequestBody Analyse analyse) {
        Analyse createdAnalyse = analyseService.saveAnalyse(analyse);
        return new ResponseEntity<>(createdAnalyse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Analyse>> getAllAnalyses() {
        List<Analyse> analyses = analyseService.getAllAnalyses();
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Analyse> getAnalyseById(@PathVariable Long id) {
        return analyseService.getAnalyseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Analyse> updateAnalyse(@PathVariable Long id, @RequestBody Analyse analyse) {
        return analyseService.getAnalyseById(id)
                .map(existingAnalyse -> {
                    analyse.setId(id);
                    Analyse updatedAnalyse = analyseService.saveAnalyse(analyse);
                    return ResponseEntity.ok(updatedAnalyse);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnalyse(@PathVariable Long id) {
        if (analyseService.getAnalyseById(id).isPresent()) {
            analyseService.deleteAnalyse(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/laboratoire/{laboratoireId}")
    public ResponseEntity<String> getAnalysesByLaboratoire(@PathVariable Long laboratoireId) {
       String name = analyseService.getAnalyseByLaboratoire(laboratoireId);
        return ResponseEntity.ok(name);
    }
}