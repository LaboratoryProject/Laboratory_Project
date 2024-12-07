package com.laboratoire.laboratoire_service.dto;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public class LaboratoireDTO {
    private Long Id;
    private String nom;
    private String logo;
    private String nrc;
    private boolean active;
    private LocalDate dateActivation;

    // Constructeurs
    public LaboratoireDTO() {}

    public LaboratoireDTO(String nom, String logo, String nrc, boolean active, LocalDate dateActivation) {
        this.nom = nom;
        this.logo = logo;
        this.nrc = nrc;
        this.active = active;
        this.dateActivation = dateActivation;
    }

    // Getters et Setters
    // ... (à ajouter)

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDate getDateActivation() {
        return dateActivation;
    }

    public void setDateActivation(LocalDate dateActivation) {
        this.dateActivation = dateActivation;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}