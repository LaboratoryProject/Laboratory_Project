package com.laboratoire.laboratoire_service.dto;

public class AdresseDTO{
      private  Long Id;
      private String numVoie;
      private String nomVoie;
      private String codePostal;
      private String ville;
      private String commune;
      private static Long count= 0L;

    public Long getId() {
        return this.Id;
    }

    public AdresseDTO() {
        this.Id=count;
        count++;

    }
    public AdresseDTO(Long id, String numVoie, String nomVoie, String codePostal, String ville, String commune) {
        Id = id;
        this.numVoie = numVoie;
        this.nomVoie = nomVoie;
        this.codePostal = codePostal;
        this.ville = ville;
        this.commune = commune;
    }


    public AdresseDTO( String numVoie, String nomVoie, String codePostal, String ville, String commune) {

        this.numVoie = numVoie;
        this.nomVoie = nomVoie;
        this.codePostal = codePostal;
        this.ville = ville;
        this.commune = commune;
    }


    public void setId(Long id) {
        Id = id;
    }

    public String getNumVoie() {
        return numVoie;
    }

    public void setNumVoie(String numVoie) {
        this.numVoie = numVoie;
    }

    public String getNomVoie() {
        return nomVoie;
    }

    public void setNomVoie(String nomVoie) {
        this.nomVoie = nomVoie;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }
}