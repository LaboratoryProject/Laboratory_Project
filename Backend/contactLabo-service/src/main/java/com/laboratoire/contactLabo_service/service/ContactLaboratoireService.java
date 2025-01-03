package com.laboratoire.contactLabo_service.service;

import com.laboratoire.contactLabo_service.model.ContactLaboratoire;
import com.laboratoire.contactLabo_service.repository.ContactLaboratoireRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactLaboratoireService {

    private final ContactLaboratoireRepository contactLaboratoireRepository;

    @Autowired
    public ContactLaboratoireService(ContactLaboratoireRepository contactLaboratoireRepository) {
        this.contactLaboratoireRepository = contactLaboratoireRepository;
    }

    public List<ContactLaboratoire> getAllContacts() {
        return contactLaboratoireRepository.findAll();
    }

    public Optional<ContactLaboratoire> getContactById(Long id) {
        return Optional.ofNullable(contactLaboratoireRepository.findByFkIdLaboratoire(id));
    }

    public ContactLaboratoire saveContact(ContactLaboratoire contactLaboratoire) {
        return contactLaboratoireRepository.save(contactLaboratoire);
    }

  public Long getContactByIdLabo (Long id){
        return contactLaboratoireRepository.findByFkIdLaboratoire(id).getId();
  }

  public Long getAdresseByIdLabo (Long id){
        return contactLaboratoireRepository.findByFkIdLaboratoire(id).getFkIdAdresse();
    }

    public void deleteContact(Long id) {
        contactLaboratoireRepository.deleteById(id);
    }
    @Transactional
    public ContactLaboratoire updateContact(Long id, ContactLaboratoire updatedContact) {
        return contactLaboratoireRepository.findById(id).map(contact -> {
            contact.setFkIdLaboratoire(updatedContact.getFkIdLaboratoire());
            contact.setFkIdAdresse(updatedContact.getFkIdAdresse());
            contact.setNumTel(updatedContact.getNumTel());
            contact.setFax(updatedContact.getFax());
            contact.setEmail(updatedContact.getEmail());
            return contactLaboratoireRepository.save(contact);
        }).orElseThrow(() -> new RuntimeException("Contact non trouvé avec l'id : " + id));
    }
}
