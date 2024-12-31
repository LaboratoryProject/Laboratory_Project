package com.laboratoire.adresse_service.model;

import jakarta.persistence.*;

@Entity
@Table(name = "adresse")
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identifiant unique de l'adresse

    @Column(name = "num_voie", nullable = false, length = 10)
    private String numVoie; // Numéro de la voie (rue, avenue)

    @Column(name = "nom_voie", nullable = false, length = 100)
    private String nomVoie; // Nom de la voie

    @Column(name = "code_postal", nullable = false, length = 10)
    private String codePostal; // Code postal de la localité

    @Column(name = "ville", nullable = false, length = 50)
    private String ville; // Ville où se situe l'adresse

    @Column(name = "commune", length = 50)
    private String commune; // Commune associée à l'adresse

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
