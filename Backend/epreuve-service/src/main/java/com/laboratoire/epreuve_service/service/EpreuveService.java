package com.laboratoire.epreuve_service.service;

import com.laboratoire.epreuve_service.model.Epreuve;
import com.laboratoire.epreuve_service.repository.EpreuveRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public interface EpreuveService {


    List<Epreuve> findAll() ;

    Optional<Epreuve> findById(Long id) ;

    List<Epreuve> findByAnalyse(Long analyseId) ;
    Epreuve save(Epreuve epreuve);

    void deleteById(Long id);

    List<Epreuve> searchByNom(String nom);

     boolean existsById(Long id) ;
}