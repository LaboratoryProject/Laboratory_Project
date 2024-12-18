package com.laboratoire.patient_service.repository;


import com.laboratoire.patient_service.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    // Vous pouvez ajouter des requêtes personnalisées ici si nécessaire, par exemple :
    Patient findByEmail(String email);
}
