package com.laboratoire.dossier_service.dto;

public class DossierDTO {
    private String numDossier; // Numéro unique d’identification du dossier
    private String fkEmailUtilisateur; // Référence à l’utilisateur associé
    private Long fkIdPatient; // Référence au patient documenté dans le dossier
    private String date; // Date de création ou mise à jour du dossier

    // Constructeurs par défaut et paramétrés
    public DossierDTO() {}

    public DossierDTO(String numDossier, String fkEmailUtilisateur, Long fkIdPatient, String date) {
        this.numDossier = numDossier;
        this.fkEmailUtilisateur = fkEmailUtilisateur;
        this.fkIdPatient = fkIdPatient;
        this.date = date;
    }

    // Getters et Setters
    public String getNumDossier() {
        return numDossier;
    }

    public void setNumDossier(String numDossier) {
        this.numDossier = numDossier;
    }

    public String getFkEmailUtilisateur() {
        return fkEmailUtilisateur;
    }

    public void setFkEmailUtilisateur(String fkEmailUtilisateur) {
        this.fkEmailUtilisateur = fkEmailUtilisateur;
    }

    public Long getFkIdPatient() {
        return fkIdPatient;
    }

    public void setFkIdPatient(Long fkIdPatient) {
        this.fkIdPatient = fkIdPatient;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
