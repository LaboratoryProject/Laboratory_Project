package com.laboratoire.laboratoire_service.feign;

import com.laboratoire.laboratoire_service.dto.ContactLaboratoireDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "contactLabo-service")
public interface ContactClient {

    @PostMapping("/api/contact-laboratoire")
    ResponseEntity<ContactLaboratoireDTO> createContact(ContactLaboratoireDTO contactDTO);

    @PutMapping("/api/contact-laboratoire/{id}")
    ResponseEntity<ContactLaboratoireDTO> updateContact(ContactLaboratoireDTO contactDTO);
    @DeleteMapping("/api/contact-laboratoire/{id}")
    ResponseEntity<Void> deleteContact(@PathVariable Long id);  // Added @PathVariable

    @GetMapping("/api/contact-laboratoire/idLaboratoire/{id}")  // Added "/" before api
    ResponseEntity<Long> getIdContact(@PathVariable Long id);

    @GetMapping("/api/contact-laboratoire/getcontact/{id}")
    ResponseEntity<ContactLaboratoireDTO> getContactById(@PathVariable Long id);// Added @PathVariable

    @GetMapping("/api/contact-laboratoire/idAdresse/{id}")  // Added "/" before api
    Long getIdAdresse(@PathVariable Long id);  // Added @PathVariable
}

