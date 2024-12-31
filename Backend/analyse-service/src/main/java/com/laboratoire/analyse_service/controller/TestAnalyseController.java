package com.laboratoire.analyse_service.controller;

import com.laboratoire.analyse_service.model.TestAnalyse;
import com.laboratoire.analyse_service.service.TestAnalyseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/an/test-analyses")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TestAnalyseController {

    private final TestAnalyseService testAnalyseService;

    @Autowired
    public TestAnalyseController(TestAnalyseService testAnalyseService) {
        this.testAnalyseService = testAnalyseService;
    }

    @PostMapping
    public ResponseEntity<TestAnalyse> ajouterTestAnalyse(@RequestBody TestAnalyse testAnalyse) {
        TestAnalyse nouveauTestAnalyse = testAnalyseService.ajouterTestAnalyse(testAnalyse);
        return ResponseEntity.status(201).body(nouveauTestAnalyse);
    }

    @GetMapping
    public ResponseEntity<List<TestAnalyse>> getAllTestAnalyses() {
        List<TestAnalyse> testAnalyses = testAnalyseService.getAllTestAnalyses();
        return ResponseEntity.ok(testAnalyses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestAnalyse> getTestAnalyseById(@PathVariable int id) {
        return testAnalyseService.getTestAnalyseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerTestAnalyse(@PathVariable int id) {
        if (testAnalyseService.supprimerTestAnalyse(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
