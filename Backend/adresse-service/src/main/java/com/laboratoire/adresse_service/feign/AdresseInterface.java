package com.laboratoire.adresse_service.feign;

import com.laboratoire.adresse_service.dto.AdresseDTO;
import com.laboratoire.adresse_service.model.Adresse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "adresse-service")

public interface AdresseInterface {
    @PostMapping("/adresses")
    ResponseEntity<Adresse> creerAdresse(@RequestBody AdresseDTO adresseDTO);
}
