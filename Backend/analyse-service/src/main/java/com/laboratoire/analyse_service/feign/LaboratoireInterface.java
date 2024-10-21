package com.laboratoire.analyse_service.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value="laboratoire" , url="http://localhost:8081")
public interface LaboratoireInterface {

    @RequestMapping(method= RequestMethod.GET, value="/api/laboratoire/{id}")
    ResponseEntity<?> getLaboratoireById(@PathVariable Long id);

}
