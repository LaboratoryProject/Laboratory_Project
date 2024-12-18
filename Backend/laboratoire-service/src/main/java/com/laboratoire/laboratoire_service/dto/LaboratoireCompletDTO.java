package com.laboratoire.laboratoire_service.dto;


import com.laboratoire.laboratoire_service.model.Laboratoire;

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

    public void setLaboratoireAvecLabo(Laboratoire laboratoire) {
        this.laboratoire.setNom(laboratoire.getNom());
        this.laboratoire.setNrc(laboratoire.getNrc());
        this.laboratoire.setLogo(laboratoire.getLogo());
        this.laboratoire.setDateActivation(laboratoire.getDateActivation());
        this.laboratoire.setActive(laboratoire.isActive());

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

}