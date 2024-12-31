package com.laboratoire.analyse_service.service;

import com.laboratoire.analyse_service.model.Epreuve;
import com.laboratoire.analyse_service.model.EpreuveDetailsDTO;

import java.util.List;
import java.util.Optional;

public interface EpreuveService {
    Epreuve ajouterEpreuve(Epreuve epreuve, int testAnalyseId);
    List<Epreuve> getAllEpreuves();
    Optional<Epreuve> getEpreuveById(int id);
    boolean supprimerEpreuve(int id);
    Optional<EpreuveDetailsDTO> getEpreuveDetails(int epreuveId);
}
