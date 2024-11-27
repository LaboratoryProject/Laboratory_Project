package com.laboratoire.laboratoire_service.feign;

import com.laboratoire.laboratoire_service.dto.ContactLaboratoireDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "contactLabo-service")
public interface ContactClient {

    @PostMapping("/api/contact-laboratoire")
    ResponseEntity<ContactLaboratoireDTO> createContact(ContactLaboratoireDTO contactDTO);
}
