package com.laboratoire.analyse_service.model;

import jakarta.persistence.*;

@Entity
public class TestAnalyse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private Double minValue;
    private Double maxValue;
    private String unite;

    // Constructeurs
    public TestAnalyse() {}

    public TestAnalyse(String name, Double minValue, Double maxValue, String unite) {
        this.name = name;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.unite = unite;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMinValue() {
        return minValue;
    }

    public void setMinValue(Double minValue) {
        this.minValue = minValue;
    }

    public Double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Double maxValue) {
        this.maxValue = maxValue;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }
}
