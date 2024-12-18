package com.laboratoire.utilisateur_service.feign;

import com.laboratoire.utilisateur_service.dto.LaboratoireDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "laboratoire-service")
public interface LaboratoireClient {
        @GetMapping("/api/laboratoires/laboratoire/{id}")
        LaboratoireDTO getLaboratoireById(@PathVariable Long id);

        @GetMapping("/api/laboratoires/findIdByNrc")
        ResponseEntity<Long> findIdByNrc(@RequestParam String nrc);}

