package com.laboratoire.utilisateur_service.dto;

import com.laboratoire.utilisateur_service.model.Role;

public class AdminUtilisateurDTO {
    private Long id;
    private String email;
    private String nomComplet;
    private String profession;
    private String numTel;
    private Role role;

    // Informations du laboratoire
    private Long laboratoireId;
    private String laboratoireNom;
    private String laboratoireNrc;

    // Constructeurs
    public AdminUtilisateurDTO() {}

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getLaboratoireId() {
        return laboratoireId;
    }

    public void setLaboratoireId(Long laboratoireId) {
        this.laboratoireId = laboratoireId;
    }

    public String getLaboratoireNom() {
        return laboratoireNom;
    }

    public void setLaboratoireNom(String laboratoireNom) {
        this.laboratoireNom = laboratoireNom;
    }

    public String getLaboratoireNrc() {
        return laboratoireNrc;
    }

    public void setLaboratoireNrc(String laboratoireNrc) {
        this.laboratoireNrc = laboratoireNrc;
    }

    public void setLaboratoireDetails(LaboratoireDTO laboratoire) {
        if (laboratoire != null) {
            this.laboratoireId = laboratoire.getId();
            this.laboratoireNom = laboratoire.getNom();
            this.laboratoireNrc = laboratoire.getNrc();
        }
    }
}