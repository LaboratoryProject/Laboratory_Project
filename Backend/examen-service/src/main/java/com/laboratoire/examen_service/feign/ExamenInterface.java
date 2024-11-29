package com.laboratoire.examen_service.feign;

import com.laboratoire.examen_service.dto.ExamenDTO;
import com.laboratoire.examen_service.model.Examen;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "examen-service")
public interface ExamenInterface {

    @PostMapping("/examens")
    ResponseEntity<Examen> creerExamen(@RequestBody ExamenDTO examenDTO);
}
