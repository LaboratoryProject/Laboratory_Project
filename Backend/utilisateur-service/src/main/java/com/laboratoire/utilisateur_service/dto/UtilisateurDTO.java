package com.laboratoire.utilisateur_service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.laboratoire.utilisateur_service.model.Role;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UtilisateurDTO {
    private Long id;
    private String email;
    private String nomComplet;
    private String profession;
    private String numTel;
    private String signature;
    private Role role;
    private String password;
    @JsonProperty("nrc")
    private String nrc;


    public UtilisateurDTO() {

    }


    public UtilisateurDTO(Long id, String email, String nomComplet,
                          String profession, String numTel, String signature,
                          Role role, String password, String nrc) {
        this.id=id;
        this.email = email;
        this.nomComplet = nomComplet;
        this.profession = profession;
        this.numTel = numTel;
        this.signature = signature;
        this.role = role;
        this.password = password;
        this.nrc = nrc;
    }
    public UtilisateurDTO( String email, String nomComplet,
                           String profession, String numTel,
                           String signature, Role role,
                           String password, String nrc) {

        this.email = email;
        this.nomComplet = nomComplet;
        this.profession = profession;
        this.numTel = numTel;
        this.signature = signature;
        this.role = role;
        this.password = password;
        this.nrc = nrc;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}


