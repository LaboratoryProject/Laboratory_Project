package com.laboratoire.adresse_service.controller;

import com.laboratoire.adresse_service.model.Adresse;
import com.laboratoire.adresse_service.service.AdresseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adresse")
public class AdresseController {

    @Autowired
    private AdresseService adresseService;

    // Ajouter une nouvelle adresse
    @PostMapping
    public ResponseEntity<Adresse> createAdresse(@RequestBody Adresse adresse) {
        Adresse savedAdresse = adresseService.addAdresse(adresse);
        return ResponseEntity.ok(savedAdresse);
    }

    // Récupérer toutes les adresses
    @GetMapping
    public ResponseEntity<List<Adresse>> getAllAdresses() {
        List<Adresse> adresses = adresseService.getAllAdresses();
        return ResponseEntity.ok(adresses);
    }

    // Récupérer une adresse par ID
    @GetMapping("/{id}")
    public ResponseEntity<Adresse> getAdresseById(@PathVariable Long id) {
        return adresseService.getAdresseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Mettre à jour une adresse
    @PutMapping("/modifier/{id}")
    public ResponseEntity<Adresse> updateAdresse(@PathVariable Long id, @RequestBody Adresse updatedAdresse) {
        try {
            System.out.println("one");
            Adresse updated = adresseService.updateAdresse(id, updatedAdresse);
            System.out.println("two");
            return ResponseEntity.ok(updated);
        } catch (AdresseNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    // Supprimer une adresse
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdresse(@PathVariable Long id) {
        adresseService.deleteAdresse(id);
        return ResponseEntity.noContent().build();
    }
}
