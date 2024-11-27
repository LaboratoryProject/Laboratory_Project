package com.laboratoire.laboratoire_service.dto;


public class LaboratoireCompletDTO {
    private LaboratoireDTO laboratoire;
    private AdresseDTO adresse;
    private ContactLaboratoireDTO contactLaboratoire;

    // Constructeurs
    public LaboratoireCompletDTO() {}

    public LaboratoireCompletDTO(LaboratoireDTO laboratoire, AdresseDTO adresse, ContactLaboratoireDTO contactLaboratoire) {
        this.laboratoire = laboratoire;
        this.adresse = adresse;
        this.contactLaboratoire = contactLaboratoire;
    }

    public LaboratoireDTO getLaboratoire() {
        return laboratoire;
    }

    public void setLaboratoire(LaboratoireDTO laboratoire) {
        this.laboratoire = laboratoire;
    }

    public AdresseDTO getAdresse() {
        return adresse;
    }

    public void setAdresse(AdresseDTO adresse) {
        this.adresse = adresse;
    }

    public ContactLaboratoireDTO getContactLaboratoire() {
        return contactLaboratoire;
    }

    public void setContactLaboratoire(ContactLaboratoireDTO contactLaboratoire) {
        this.contactLaboratoire = contactLaboratoire;
    }
// Getters et Setters
    // ... (à ajouter)
}