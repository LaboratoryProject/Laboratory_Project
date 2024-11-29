package com.laboratoire.examen_service.dto;

public class ExamenDTO {

    private Long fkNumDossier; // Référence au dossier
    private Long fkIdEpreuve; // Référence à l'épreuve
    private Long fkIdTestAnalyse; // Référence au test d'analyse
    private String resultat; // Résultat de l'examen

    // Constructeur par défaut
    public ExamenDTO() {
    }

    // Constructeur avec tous les attributs
    public ExamenDTO(Long fkNumDossier, Long fkIdEpreuve, Long fkIdTestAnalyse, String resultat) {
        this.fkNumDossier = fkNumDossier;
        this.fkIdEpreuve = fkIdEpreuve;
        this.fkIdTestAnalyse = fkIdTestAnalyse;
        this.resultat = resultat;
    }

    // Getters et Setters
    public Long getFkNumDossier() {
        return fkNumDossier;
    }

    public void setFkNumDossier(Long fkNumDossier) {
        this.fkNumDossier = fkNumDossier;
    }

    public Long getFkIdEpreuve() {
        return fkIdEpreuve;
    }

    public void setFkIdEpreuve(Long fkIdEpreuve) {
        this.fkIdEpreuve = fkIdEpreuve;
    }

    public Long getFkIdTestAnalyse() {
        return fkIdTestAnalyse;
    }

    public void setFkIdTestAnalyse(Long fkIdTestAnalyse) {
        this.fkIdTestAnalyse = fkIdTestAnalyse;
    }

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }
}
