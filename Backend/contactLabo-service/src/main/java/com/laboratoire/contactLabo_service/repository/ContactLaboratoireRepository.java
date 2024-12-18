package com.laboratoire.contactLabo_service.repository;


import com.laboratoire.contactLabo_service.model.ContactLaboratoire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactLaboratoireRepository extends JpaRepository<ContactLaboratoire, Long> {
    ContactLaboratoire findByFkIdLaboratoire(Long FkIdLaboratoire);
    ContactLaboratoire findByfkIdAdresse(Long fkIdAdresse);
}
