package com.laboratoire.patient_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laboratoire.patient_service.controller.PatientController;
import com.laboratoire.patient_service.model.Patient;
import com.laboratoire.patient_service.service.PatientService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest // Load the full context
@AutoConfigureMockMvc // Auto-configure MockMvc for testing the web layer
class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PatientService patientService;

    @Test
    void ajouterPatientTest() throws Exception {
        // Arrange
        Patient patient = new Patient(14L, "John Doe", "1990-01-01", "Paris", "Male",
                "Passport", "123456789", "123 Main St",
                "0123456789", "john.doe@example.com", true);

        Patient savedPatient = new Patient(1L, "John Doe", "1990-01-01", "Paris", "Male",
                "Passport", "123456789", "123 Main St",
                "0123456789", "john.doe@example.com");

        when(patientService.ajouterPatient(any(Patient.class))).thenReturn(savedPatient);

        // Act & Assert
        mockMvc.perform(post("/api/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patient)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nomComplet").value("John Doe"));
    }

    @Test
    void obtenirTousLesPatientsTest() throws Exception {
        // Arrange
        List<Patient> patients = Arrays.asList(
                new Patient(1L, "John Doe", "1990-01-01", "Paris", "Male",
                        "Passport", "123456789", "123 Main St",
                        "0123456789", "john.doe@example.com"),
                new Patient(2L, "Jane Doe", "1985-05-15", "London", "Female",
                        "ID Card", "987654321", "456 Elm St",
                        "0987654321", "jane.doe@example.com")
        );

        when(patientService.obtenirTousLesPatients()).thenReturn(patients);

        // Act & Assert
        mockMvc.perform(get("/api/patients")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].nomComplet").value("John Doe"))
                .andExpect(jsonPath("$[1].nomComplet").value("Jane Doe"));
    }

    @Test
    void obtenirPatientParIdTest() throws Exception {
        // Arrange
        Long id = 1L;
        Patient patient = new Patient(id, "John Doe", "1990-01-01", "Paris", "Male",
                "Passport", "123456789", "123 Main St",
                "0123456789", "john.doe@example.com");

        when(patientService.obtenirPatientParId(anyLong())).thenReturn(patient);

        // Act & Assert
        mockMvc.perform(get("/api/patients/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nomComplet").value("John Doe"));
    }

    @Test
    void supprimerPatientTest() throws Exception {
        // Arrange
        Long id = 1L;
        Mockito.doNothing().when(patientService).supprimerPatient(anyLong());

        // Act & Assert
        mockMvc.perform(delete("/api/patients/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
