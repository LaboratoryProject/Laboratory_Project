package com.laboratoire.analyse_service.service;

import com.laboratoire.analyse_service.feign.LaboratoireInterface;
import com.laboratoire.analyse_service.model.Analyse;
import com.laboratoire.analyse_service.repository.AnalyseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnalyseServiceImpl implements AnalyseService {

    private final AnalyseRepository analyseRepository;
    private final LaboratoireInterface laboratoireInterface;

    @Autowired
    public AnalyseServiceImpl(AnalyseRepository analyseRepository, LaboratoireInterface laboratoireInterface) {
        this.analyseRepository = analyseRepository;
        this.laboratoireInterface = laboratoireInterface;
    }

    @Override
    public List<Analyse> getAllAnalyses() {
        return analyseRepository.findAll();
    }

    @Override
    public Optional<Analyse> getAnalyseById(Long id) {
        return analyseRepository.findById(id);
    }

    @Override
    public Analyse saveAnalyse(Analyse analyse) {
        return analyseRepository.save(analyse);
    }

    @Override
    public void deleteAnalyse(Long id) {
        analyseRepository.deleteById(id);
    }

    @Override
    public List<Analyse> getAnalysesByLaboratoire(Long laboratoireId) {
        // Call the Feign client to fetch the laboratoire by ID
        ResponseEntity<?> laboratoireResponse = laboratoireInterface.getLaboratoireById(laboratoireId);

        // Check if the laboratoire exists
        if (laboratoireResponse.getStatusCode().is2xxSuccessful() && laboratoireResponse.getBody() != null) {
            // If the laboratory exists, fetch analyses by laboratoire ID
            return analyseRepository.findByFkIdLaboratoire(laboratoireId);
        } else {
            // Handle the case where the laboratory is not found
            throw new RuntimeException("Laboratory with ID " + laboratoireId + " not found.");
        }
    }
}
