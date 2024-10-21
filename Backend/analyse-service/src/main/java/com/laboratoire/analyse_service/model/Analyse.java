package com.laboratoire.analyse_service.model;
import jakarta.persistence.*;

@Entity
@Table(name="analyse_table")
public class Analyse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fk_id_laboratoire")
    private Long fkIdLaboratoire;

    private String nom;

    private String description;

    public Analyse() {
    }

    public Analyse(Long id, Long fkIdLaboratoire, String nom, String description) {
        this.id = id;
        this.fkIdLaboratoire = fkIdLaboratoire;
        this.nom = nom;
        this.description = description;
    }
    public Analyse(Long id,String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Analyse(Long fkIdLaboratoire, String nom, String description) {
        this.fkIdLaboratoire = fkIdLaboratoire;
        this.nom = nom;
        this.description = description;
    }

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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}