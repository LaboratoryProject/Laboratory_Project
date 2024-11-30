package com.laboratoire.laboratoire_service.feign;

import com.laboratoire.laboratoire_service.dto.AdresseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "adresse-service")
public interface AdresseClient {

    @PostMapping("/api/adresse")
    ResponseEntity<AdresseDTO> creerAdresse(AdresseDTO adresseDto);

    @PutMapping("/api/adresse/{id}")
    ResponseEntity<AdresseDTO> updateAdresse( AdresseDTO adresseDTO);

    @DeleteMapping("/api/adresse/by-laboratoire/{laboratoireId}")
    ResponseEntity<Void> deleteAdressesByLaboratoireId( Long laboratoireId);
}


