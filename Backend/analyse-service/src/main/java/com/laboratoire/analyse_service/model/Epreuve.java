package com.laboratoire.analyse_service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Epreuve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Double resultat;

    @ManyToOne
    @JoinColumn(name = "test_analyse_id", nullable = false)
    private TestAnalyse testAnalyse;

    @ManyToOne
    @JoinColumn(name = "analyse_id", nullable = false)
    @JsonBackReference
    private Analyse analyse;

    // Constructeurs
    public Epreuve() {}

    public Epreuve(Double resultat, TestAnalyse testAnalyse, Analyse analyse) {
        this.resultat = resultat;
        this.testAnalyse = testAnalyse;
        this.analyse = analyse;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getResultat() {
        return resultat;
    }

    public void setResultat(Double resultat) {
        this.resultat = resultat;
    }

    public TestAnalyse getTestAnalyse() {
        return testAnalyse;
    }

    public void setTestAnalyse(TestAnalyse testAnalyse) {
        this.testAnalyse = testAnalyse;
    }

    public Analyse getAnalyse() {
        return analyse;
    }

    public void setAnalyse(Analyse analyse) {
        this.analyse = analyse;
    }
}
