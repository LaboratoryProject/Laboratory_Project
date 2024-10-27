package com.laboratoire.epreuve_service.model;
import jakarta.persistence.*;


@Entity
@Table(name = "epreuve")
public class Epreuve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fk_id_analyse", nullable = false)
    private Long fkIdAnalyse;

    @Column(nullable = false)
    private String nom;

    public Epreuve() {

    }
    public Epreuve( Long fkIdAnalyse, String nom) {
        this.fkIdAnalyse = fkIdAnalyse;
        this.nom = nom;
    }


    public Epreuve(Long id, Long fkIdAnalyse, String nom) {
        this.id = id;
        this.fkIdAnalyse = fkIdAnalyse;
        this.nom = nom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFkIdAnalyse() {
        return fkIdAnalyse;
    }

    public void setFkIdAnalyse(Long fkIdAnalyse) {
        this.fkIdAnalyse = fkIdAnalyse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}