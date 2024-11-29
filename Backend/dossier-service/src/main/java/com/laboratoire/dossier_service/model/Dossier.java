package com.laboratoire.dossier_service.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "dossier")
public class Dossier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numDossier; // Numéro unique d'identification du dossier

    @Column(name = "fk_email_utilisateur", nullable = false, length = 100)
    private String fkEmailUtilisateur; // Référence à l'utilisateur auquel le dossier est associé

    @Column(name = "fk_id_patient", nullable = false)
    private Long fkIdPatient; // Référence au patient documenté dans le dossier

    @Column(name = "date", nullable = false)
    private LocalDate date; // Date de création ou de mise à jour du dossier

    // Getters et Setters
    public Long getNumDossier() {
        return numDossier;
    }

    public void setNumDossier(Long numDossier) {
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
