package com.laboratoire.laboratoire_service.feign;

import com.laboratoire.laboratoire_service.dto.AdresseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "adresse-service")
public interface AdresseClient {

    @PostMapping("/api/adresse")
    ResponseEntity<AdresseDTO> creerAdresse(AdresseDTO adresseDto);


    @PutMapping("/api/adresse/modifier/{id}")
    ResponseEntity<AdresseDTO> updateAdresse(@PathVariable Long id, @RequestBody AdresseDTO adresse);


    @DeleteMapping("/api/adresse/{laboratoireId}")
    ResponseEntity<Void> deleteAdresse(@PathVariable Long laboratoireId);

    @GetMapping("/api/adresse/{id}")
    ResponseEntity<AdresseDTO> getAdresseById(@PathVariable Long id);
}



