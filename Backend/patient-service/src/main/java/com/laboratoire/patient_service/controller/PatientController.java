package com.laboratoire.patient_service.controller;

import com.laboratoire.patient_service.model.Patient;
import com.laboratoire.patient_service.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE ,RequestMethod.PATCH})
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping
    public ResponseEntity<Patient> ajouterPatient(@RequestBody Patient patient) {
        Patient nouveauPatient = patientService.ajouterPatient(patient);
        return ResponseEntity.ok(nouveauPatient);
    }

    @GetMapping
    public ResponseEntity<List<Patient>> obtenirTousLesPatients() {
        List<Patient> patients = patientService.obtenirTousLesPatients();
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> obtenirPatientParId(@PathVariable Long id) {
        Patient patient = patientService.obtenirPatientParId(id);
        return patient != null ? ResponseEntity.ok(patient) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerPatient(@PathVariable Long id) {
        patientService.supprimerPatient(id);
        return ResponseEntity.noContent().build();
    }
}
