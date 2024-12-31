package com.laboratoire.laboratoire_service.dto;

import java.time.LocalDate;

public record LaboratoireResponse(
        String nom,
        String logo,
        byte[] nrc,
        String laboratoireNrc, boolean active,
        LocalDate dateActivation
) {}
