package com.laboratoire.contactLabo_service.controller;

import com.laboratoire.contactLabo_service.model.ContactLaboratoire;
import com.laboratoire.contactLabo_service.service.ContactLaboratoireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact-laboratoire")
public class ContactLaboratoireController {

    private final ContactLaboratoireService contactLaboratoireService;

    @Autowired
    public ContactLaboratoireController(ContactLaboratoireService contactLaboratoireService) {
        this.contactLaboratoireService = contactLaboratoireService;
    }

    @GetMapping
    public List<ContactLaboratoire> getAllContacts() {
        return contactLaboratoireService.getAllContacts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactLaboratoire> getContactById(@PathVariable Long id) {
        return contactLaboratoireService.getContactById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ContactLaboratoire> createContact(@RequestBody ContactLaboratoire contactLaboratoire) {
        ContactLaboratoire cts = contactLaboratoireService.saveContact(contactLaboratoire);
        return ResponseEntity.ok(cts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactLaboratoire> updateContact(@PathVariable Long id, @RequestBody ContactLaboratoire updatedContact) {
        try {
            return ResponseEntity.ok(contactLaboratoireService.updateContact(id, updatedContact));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        contactLaboratoireService.deleteContact(id);
        return ResponseEntity.noContent().build();
    }
}
