package com.laboratoire.contactLabo_service.feign;
import com.laboratoire.contactLabo_service.dto.ContactLaboratoireDTO;
import com.laboratoire.contactLabo_service.model.ContactLaboratoire;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "contact-service")
public interface ContactLaboratoireInterface {
    @PostMapping("/contacts")
    ResponseEntity<ContactLaboratoire> creerContact(@RequestBody ContactLaboratoireDTO contactDTO);
}