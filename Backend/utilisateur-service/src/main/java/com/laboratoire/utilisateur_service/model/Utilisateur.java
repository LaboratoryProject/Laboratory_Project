package com.laboratoire.utilisateur_service.model;
import com.laboratoire.utilisateur_service.dto.UtilisateurDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "utilisateurs")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getKeycloakId() {
        return keycloakId;
    }

    public void setKeycloakId(String keycloakId) {
        this.keycloakId = keycloakId;
    }


    public Utilisateur(Long id, String keycloakId, String email, Long fkIdLaboratoire, String nomComplet, String profession, String numTel, String signature, Role role, String password) {
        this.id = id;
        this.keycloakId = keycloakId;
        this.email = email;
        this.fkIdLaboratoire = fkIdLaboratoire;
        this.nomComplet = nomComplet;
        this.profession = profession;
        this.numTel = numTel;
        this.signature = signature;
        this.role = role;
        this.password = password;
    }


    public Utilisateur() {
    }

    public Utilisateur(Long id, String email, Long fkIdLaboratoire, String nomComplet, String profession, String numTel, String signature, Role role, String password) {
        this.id = id;
        this.email = email;
        this.fkIdLaboratoire = fkIdLaboratoire;
        this.nomComplet = nomComplet;
        this.profession = profession;
        this.numTel = numTel;
        this.signature = signature;
        this.role = role;
        this.password = password;
    }
    public Utilisateur(UtilisateurDTO utilisateur) {
        this.id = utilisateur.getId();
        this.email = utilisateur.getEmail();
        this.nomComplet = utilisateur.getNomComplet();
        this.profession = utilisateur.getProfession();
        this.numTel = utilisateur.getNumTel();
        this.signature = utilisateur.getSignature();
        this.role = utilisateur.getRole();
        this.password = utilisateur.getPassword();
    }

    @Column(name = "KEYCLOAK_ID", unique = true)
    private String keycloakId;

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

    @Column(name = "password")
    private String password;


    public Utilisateur( String email, Long fkIdLaboratoire, String nomComplet, String profession, String numTel, String signature, Role role) {
        this.email = email;
        this.fkIdLaboratoire = fkIdLaboratoire;
        this.nomComplet = nomComplet;
        this.profession = profession;
        this.numTel = numTel;
        this.signature = signature;
        this.role = role;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

