package com.laboratoire.adresse_service.service;

import com.laboratoire.adresse_service.model.Adresse;
import com.laboratoire.adresse_service.repository.AdresseRepository;
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
    public Adresse updateAdresse(Long id, Adresse updatedAdresse) {
        return adresseRepository.findById(id).map(adresse -> {
            adresse.setNumVoie(updatedAdresse.getNumVoie());
            adresse.setNomVoie(updatedAdresse.getNomVoie());
            adresse.setCodePostal(updatedAdresse.getCodePostal());
            adresse.setVille(updatedAdresse.getVille());
            adresse.setCommune(updatedAdresse.getCommune());
            return adresseRepository.save(adresse);
        }).orElseThrow(() -> new RuntimeException("Adresse non trouvée pour l'id : " + id));
    }

    // Supprimer une adresse
    public void deleteAdresse(Long id) {
        adresseRepository.deleteById(id);
    }
}
