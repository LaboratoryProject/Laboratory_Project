package com.laboratoire.adresse_service.service;

import com.laboratoire.adresse_service.model.Adresse;
import com.laboratoire.adresse_service.repository.AdresseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdresseService {

    @Autowired
    private AdresseRepository adresseRepository;

    // Ajouter une nouvelle adresse
    public Adresse addAdresse(Adresse adresse) {
        return adresseRepository.save(adresse);
    }

    // Récupérer toutes les adresses
    public List<Adresse> getAllAdresses() {
        return adresseRepository.findAll();
    }

    // Récupérer une adresse par ID
    public Optional<Adresse> getAdresseById(Long id) {
        return adresseRepository.findById(id);
    }

    // Mettre à jour une adresse
    @Transactional
    public Adresse updateAdresse(Long id, Adresse updatedAdresse) {
        System.out.println("wili");
        return adresseRepository.findById(id).map(adresse -> {
            System.out.println("kayna");
            adresse.setNumVoie(updatedAdresse.getNumVoie());
            System.out.println("oui oui");
            adresse.setNomVoie(updatedAdresse.getNomVoie());
            adresse.setCodePostal(updatedAdresse.getCodePostal());
            System.out.println("oui oui oui");
            adresse.setVille(updatedAdresse.getVille());
            adresse.setCommune(updatedAdresse.getCommune());
            System.out.println("oui oui oui oui");
            Adresse savedAdresse = adresseRepository.save(adresse);

            // Log the saved address
            System.out.println("Saved Adresse: " + savedAdresse);
            System.out.println("Saved Adresse: " + savedAdresse.getCommune());
            System.out.println("Saved Adresse: " + savedAdresse.getVille());

            return savedAdresse;
        }).orElseThrow(() -> new RuntimeException("Adresse non trouvée pour l'id : " + id));
    }

    // Supprimer une adresse
    public void deleteAdresse(Long id) {
        adresseRepository.deleteById(id);
    }
}
