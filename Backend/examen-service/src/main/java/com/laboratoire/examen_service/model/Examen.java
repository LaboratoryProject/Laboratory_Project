package com.laboratoire.examen_service.model;

import jakarta.persistence.*;

@Entity
@Table(name = "examen")
public class Examen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identifiant unique de l'examen

    @Column(name = "fk_num_dossier", nullable = false)
    private Long fkNumDossier; // Référence au dossier auquel l'examen est rattaché

    @Column(name = "fk_id_epreuve", nullable = false)
    private Long fkIdEpreuve; // Référence à l'épreuve associée à l'examen

    @Column(name = "fk_id_test_analyse", nullable = false)
    private Long fkIdTestAnalyse; // Référence au test d'analyse associé

    @Column(name = "resultat", nullable = false, length = 255)
    private String resultat; // Résultat de l'examen

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
