package com.laboratoire.utilisateur_service.dto;


import java.time.LocalDate;

public class LaboratoireDTO {
    private Long id;
    private String nom;
    private String logo;
    private String nrc;
    private boolean active;
    private LocalDate dateActivation;

    // Constructeurs
    public LaboratoireDTO() {}

    public LaboratoireDTO(Long id, String nom, String nrc) {
        this.id = id;
        this.nom = nom;
        this.nrc = nrc;
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
