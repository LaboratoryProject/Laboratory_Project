package com.laboratoire.laboratoire_service.feign;

import com.laboratoire.laboratoire_service.dto.ContactLaboratoireDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "contactLabo-service")
public interface ContactClient {

    @PostMapping("/api/contact-laboratoire")
    ResponseEntity<ContactLaboratoireDTO> createContact(ContactLaboratoireDTO contactDTO);

    @PutMapping("/api/contact-laboratoire/{id}")
    ResponseEntity<ContactLaboratoireDTO> updateContact(ContactLaboratoireDTO contactDTO);

    @DeleteMapping("/api/contact-laboratoire/{id}")
    ResponseEntity<Void> deleteContactsByLaboratoireId( Long laboratoireId);
}
