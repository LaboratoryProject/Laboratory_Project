package com.laboratoire.analyse_service.service;

import com.laboratoire.analyse_service.model.Epreuve;
import com.laboratoire.analyse_service.model.EpreuveDetailsDTO;
import com.laboratoire.analyse_service.model.TestAnalyse;
import com.laboratoire.analyse_service.repository.EpreuveRepository;
import com.laboratoire.analyse_service.repository.TestAnalyseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EpreuveServiceImpl implements EpreuveService {

    @Autowired
    private EpreuveRepository epreuveRepository;

    @Autowired
    private TestAnalyseRepository testAnalyseRepository;

    @Override
    public Epreuve ajouterEpreuve(Epreuve epreuve, int testAnalyseId) {
        TestAnalyse testAnalyse = testAnalyseRepository.findById(testAnalyseId)
                .orElseThrow(() -> new IllegalArgumentException("TestAnalyse introuvable avec l'ID : " + testAnalyseId));
        epreuve.setTestAnalyse(testAnalyse);

        return epreuveRepository.save(epreuve);
    }

    @Override
    public List<Epreuve> getAllEpreuves() {
        return epreuveRepository.findAll();
    }

    @Override
    public Optional<Epreuve> getEpreuveById(int id) {
        return epreuveRepository.findById(id);
    }

    @Override
    public boolean supprimerEpreuve(int id) {
        if (epreuveRepository.existsById(id)) {
            epreuveRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<EpreuveDetailsDTO> getEpreuveDetails(int epreuveId) {
        return epreuveRepository.findById(epreuveId).map(epreuve -> {
            TestAnalyse testAnalyse = epreuve.getTestAnalyse();
            return new EpreuveDetailsDTO(
                    testAnalyse.getMinValue(),
                    testAnalyse.getMaxValue(),
                    epreuve.getResultat(),
                    testAnalyse.getUnite()
            );
        });
    }
}
