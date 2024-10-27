package com.laboratoire.utilisateur_service.model;
import jakarta.persistence.*;

@Entity
@Table(name = "utilisateurs")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    private Long fkIdLaboratoire;

    @Column(name = "nom_complet", nullable = false)
    private String nomComplet;

    @Column(nullable = false)
    private String profession;

    @Column(name = "num_tel")
    private String numTel;

    private String signature;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public Utilisateur() {
    }

    public Utilisateur(Long id, String email, Long fkIdLaboratoire, String nomComplet, String profession, String numTel, String signature, Role role) {
        this.id = id;
        this.email = email;
        this.fkIdLaboratoire = fkIdLaboratoire;
        this.nomComplet = nomComplet;
        this.profession = profession;
        this.numTel = numTel;
        this.signature = signature;
        this.role = role;
    }
    public Utilisateur( String email, Long fkIdLaboratoire, String nomComplet, String profession, String numTel, String signature, Role role) {
        this.email = email;
        this.fkIdLaboratoire = fkIdLaboratoire;
        this.nomComplet = nomComplet;
        this.profession = profession;
        this.numTel = numTel;
        this.signature = signature;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getFkIdLaboratoire() {
        return fkIdLaboratoire;
    }

    public void setFkIdLaboratoire(Long fkIdLaboratoire) {
        this.fkIdLaboratoire = fkIdLaboratoire;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

