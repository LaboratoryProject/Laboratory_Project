package com.laboratoire.patient_service.model;


import jakarta.persistence.*;

@Entity
@Table(name = "patients") // Nom de la table dans la base de données
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Génération automatique de l'ID
    private Long id;

    @Column(nullable = false) // Champ obligatoire
    private String nomComplet;

    @Column(nullable = false)
    private String dateNaissance;

    @Column(nullable = false)
    private String lieuDeNaissance;

    @Column(nullable = false)
    private String sexe;

    @Column(nullable = false)
    private String typePieceIdentite;

    @Column(nullable = false, unique = true) // Unicité pour éviter des doublons
    private String numPieceIdentite;

    private String adresse;

    @Column(nullable = false)
    private String numTel;

    @Column(nullable = false, unique = true) // Email unique
    private String email;

    private boolean visiblePour;

    // Constructeurs, Getters et Setters

    public Patient() {}

    public Patient(String nomComplet, String dateNaissance, String lieuDeNaissance,
                   String sexe, String typePieceIdentite, String numPieceIdentite,
                   String adresse, String numTel, String email, boolean visiblePour) {
        this.nomComplet = nomComplet;
        this.dateNaissance = dateNaissance;
        this.lieuDeNaissance = lieuDeNaissance;
        this.sexe = sexe;
        this.typePieceIdentite = typePieceIdentite;
        this.numPieceIdentite = numPieceIdentite;
        this.adresse = adresse;
        this.numTel = numTel;
        this.email = email;
        this.visiblePour = visiblePour;
    }

    public Patient(Long l, String nomComplet, String dateNaissance, String lieuDeNaissance,
                   String sexe, String typePieceIdentite, String numPieceIdentite,
                   String adresse, String numTel, String email){
        this.id=l;
        this.nomComplet = nomComplet;
        this.dateNaissance = dateNaissance;
        this.lieuDeNaissance = lieuDeNaissance;
        this.sexe = sexe;
        this.typePieceIdentite = typePieceIdentite;
        this.numPieceIdentite = numPieceIdentite;
        this.adresse = adresse;
        this.numTel = numTel;
        this.email = email;

    }
    public Patient(Long id, String nomComplet, String dateNaissance, String lieuDeNaissance,
                   String sexe, String typePieceIdentite, String numPieceIdentite,
                   String adresse, String numTel, String email, boolean visiblePour) {
        this.id = id; // Ensure this is explicitly set
        this.nomComplet = nomComplet;
        this.dateNaissance = dateNaissance;
        this.lieuDeNaissance = lieuDeNaissance;
        this.sexe = sexe;
        this.typePieceIdentite = typePieceIdentite;
        this.numPieceIdentite = numPieceIdentite;
        this.adresse = adresse;
        this.numTel = numTel;
        this.email = email;
        this.visiblePour = visiblePour;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getLieuDeNaissance() {
        return lieuDeNaissance;
    }

    public void setLieuDeNaissance(String lieuDeNaissance) {
        this.lieuDeNaissance = lieuDeNaissance;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getTypePieceIdentite() {
        return typePieceIdentite;
    }

    public void setTypePieceIdentite(String typePieceIdentite) {
        this.typePieceIdentite = typePieceIdentite;
    }

    public String getNumPieceIdentite() {
        return numPieceIdentite;
    }

    public void setNumPieceIdentite(String numPieceIdentite) {
        this.numPieceIdentite = numPieceIdentite;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isVisiblePour() {
        return visiblePour;
    }

    public void setVisiblePour(boolean visiblePour) {
        this.visiblePour = visiblePour;
    }
}
