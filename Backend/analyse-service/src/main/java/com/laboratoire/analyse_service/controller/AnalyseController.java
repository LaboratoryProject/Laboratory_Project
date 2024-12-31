package com.laboratoire.analyse_service.controller;

import com.laboratoire.analyse_service.model.Analyse;
import com.laboratoire.analyse_service.service.AnalyseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/an/analyses")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AnalyseController {

    private final AnalyseService analyseService;

    @Autowired
    public AnalyseController(AnalyseService analyseService) {
        this.analyseService = analyseService;
    }

    @PostMapping
    public ResponseEntity<Analyse> createAnalyse(@RequestBody Analyse analyse) {
        Analyse createdAnalyse = analyseService.saveAnalyse(analyse);
        return ResponseEntity.status(201).body(createdAnalyse);
    }





    @PreAuthorize("hasRole('ROLE_ADMINN')")
    @GetMapping
    public ResponseEntity<List<Analyse>> getAllAnalyses() {
        // Ajouter cette ligne pour déboguer les autorités de l'utilisateur authentifié
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        System.out.println("les autorisationnnnnnnnnnnnnnnnn");
        authorities.forEach(authority -> System.out.println(authority.getAuthority()));
        System.out.println("lfu9");

        List<Analyse> analyses = analyseService.getAllAnalyses();
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Analyse> getAnalyseById(@PathVariable Long id) {
        return analyseService.getAnalyseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnalyse(@PathVariable Long id) {
        if (analyseService.deleteAnalyse(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/laboratoire/{laboratoireId}")
    public ResponseEntity<String> getAnalyseByLaboratoire(@PathVariable Long laboratoireId) {
        String laboratoireName = analyseService.getAnalyseByLaboratoire(laboratoireId);
        return ResponseEntity.ok(laboratoireName);
    }
}
