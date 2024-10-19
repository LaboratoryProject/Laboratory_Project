package com.laboratoire.laboratoire_service.dto;
import java.time.LocalDate;

public record LaboratoireResponse(String id, String nom, String logo, String nrc, boolean active, LocalDate dateActivation) {
}