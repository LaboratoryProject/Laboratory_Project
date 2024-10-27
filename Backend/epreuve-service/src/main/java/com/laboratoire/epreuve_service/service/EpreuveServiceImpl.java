package com.laboratoire.epreuve_service.service;

import com.laboratoire.epreuve_service.model.Epreuve;
import com.laboratoire.epreuve_service.repository.EpreuveRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class EpreuveServiceImpl implements EpreuveService {

    private final EpreuveRepository epreuveRepository;

    @Autowired
    public EpreuveServiceImpl(EpreuveRepository epreuveRepository) {
        this.epreuveRepository = epreuveRepository;
    }

    public List<Epreuve> findAll() {
        return epreuveRepository.findAll();
    }

    public Optional<Epreuve> findById(Long id) {
        return epreuveRepository.findById(id);
    }

    public List<Epreuve> findByAnalyse(Long analyseId) {
        return epreuveRepository.findByFkIdAnalyse(analyseId);
    }

    public Epreuve save(Epreuve epreuve) {
        return epreuveRepository.save(epreuve);
    }

    public void deleteById(Long id) {
        epreuveRepository.deleteById(id);
    }

    public List<Epreuve> searchByNom(String nom) {
        return epreuveRepository.findByNomContainingIgnoreCase(nom);
    }

    public boolean existsById(Long id) {
        return epreuveRepository.existsById(id);
    }
}