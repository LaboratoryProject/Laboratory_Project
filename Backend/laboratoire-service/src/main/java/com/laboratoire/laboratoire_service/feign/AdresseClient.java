package com.laboratoire.laboratoire_service.feign;

import com.laboratoire.laboratoire_service.dto.AdresseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "adresse-service")
public interface AdresseClient {

    @PostMapping("/api/adresse")
    ResponseEntity<AdresseDTO> creerAdresse(AdresseDTO adresseDto);
}
