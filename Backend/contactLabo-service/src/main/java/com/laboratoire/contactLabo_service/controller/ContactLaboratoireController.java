package com.laboratoire.contactLabo_service.controller;

import com.laboratoire.contactLabo_service.model.ContactLaboratoire;
import com.laboratoire.contactLabo_service.service.ContactLaboratoireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
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

    @GetMapping("/getcontact/{id}")
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

    @PutMapping("/modifier/{id}")
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


    @GetMapping("/idLaboratoire/{id}")
    public ResponseEntity<Long> getIdContact(@PathVariable Long id) {
        Long contactId = contactLaboratoireService.getContactByIdLabo(id);
        return ResponseEntity.ok(contactId); // Return HTTP 200 with the contact ID
    }

    @GetMapping("/idAdresse/{id}")
    public ResponseEntity<Long> getIdAdresse(@PathVariable Long id) {
        Long adresseId = contactLaboratoireService.getAdresseByIdLabo(id);
        return ResponseEntity.ok(adresseId); // Return HTTP 200 with the address ID
    }
}
