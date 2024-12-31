package com.laboratoire.analyse_service.model;

public class EpreuveDetailsDTO {
    private Double minValue;
    private Double maxValue;
    private Double currentValue;
    private String unite;

    public EpreuveDetailsDTO(Double minValue, Double maxValue, Double currentValue, String unite) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.currentValue = currentValue;
        this.unite = unite;
    }

    public static EpreuveDetailsDTO fromEpreuve(Epreuve epreuve) {
        TestAnalyse testAnalyse = epreuve.getTestAnalyse();
        return new EpreuveDetailsDTO(
                testAnalyse.getMinValue(),
                testAnalyse.getMaxValue(),
                epreuve.getResultat(),
                testAnalyse.getUnite()
        );
    }

    // Getters et Setters
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

    public Double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(Double currentValue) {
        this.currentValue = currentValue;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }
}
