package com.laboratoire.laboratoire_service.dto;

public class ContactLaboratoireDTO {
    private Long Id;
    private String numTel;
    private String fax;
    private String email;
    private Long fkIdLaboratoire;
    private Long fkIdAdresse;

    public ContactLaboratoireDTO(Long id, String numTel, String fax, String email, Long fkIdLaboratoire, Long fkIdAdresse) {
        Id = id;
        this.numTel = numTel;
        this.fax = fax;
        this.email = email;
        this.fkIdLaboratoire = fkIdLaboratoire;
        this.fkIdAdresse = fkIdAdresse;
    }

    public ContactLaboratoireDTO() {

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
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

    public Long getFkIdAdresse() {
        return fkIdAdresse;
    }

    public void setFkIdAdresse(Long fkIdAdresse) {
        this.fkIdAdresse = fkIdAdresse;
    }


}
