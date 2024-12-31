package com.laboratoire.contactLabo_service.model;
import jakarta.persistence.*;


@Entity
@Table(name = "contact_laboratoire")
public class ContactLaboratoire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identifiant unique du contact

    @Column(name = "fk_id_laboratoire", nullable = false)
    private Long fkIdLaboratoire; // Référence au laboratoire associé

    @Column(name = "fk_id_adresse", nullable = false)
    private Long fkIdAdresse; // Référence à l'adresse liée au contact

    @Column(name = "num_tel", nullable = false, length = 15)
    private String numTel; // Numéro de téléphone

    @Column(name = "fax", length = 15)
    private String fax; // Numéro de fax

    @Column(name = "email", nullable = false, unique = true)
    private String email; // Adresse email du laboratoire

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFkIdLaboratoire() {
        return fkIdLaboratoire;
    }

    public void setFkIdLaboratoire(Long fkIdLaboratoire) {
        this.fkIdLaboratoire = fkIdLaboratoire;
    }

    public Long getFkIdAdresse() {
        return fkIdAdresse;
    }

    public void setFkIdAdresse(Long fkIdAdresse) {
        this.fkIdAdresse = fkIdAdresse;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
