package com.laboratoire.analyse_service.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "laboratoire-service")  // Use the application name as registered in Eureka
public interface LaboratoireInterface {

    @GetMapping("/api/laboratoire/{id}")  // Using @GetMapping is preferred over @RequestMapping
    ResponseEntity<?> getLaboratoireById(@PathVariable("id") Long id);

    @GetMapping("/api/laboratoires/nom/{id}")
    String getLaboratoireNameById(@PathVariable("id") Long id);

}