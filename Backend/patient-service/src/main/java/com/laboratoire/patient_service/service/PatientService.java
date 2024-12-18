package com.laboratoire.patient_service.service;

import com.laboratoire.patient_service.model.Patient;
import com.laboratoire.patient_service.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient ajouterPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public List<Patient> obtenirTousLesPatients() {
        return patientRepository.findAll();
    }

    public Patient obtenirPatientParId(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    public void supprimerPatient(Long id) {
        patientRepository.deleteById(id);
    }
}
