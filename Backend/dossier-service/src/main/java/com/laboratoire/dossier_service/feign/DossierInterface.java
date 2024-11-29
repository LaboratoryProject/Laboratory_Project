package com.laboratoire.dossier_service.feign;

import com.laboratoire.dossier_service.dto.DossierDTO;
import com.laboratoire.dossier_service.model.Dossier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "dossier-service")
public interface DossierInterface {

    @PostMapping("/dossiers")
    ResponseEntity<Dossier> creerDossier(@RequestBody DossierDTO dossierDTO);
}
