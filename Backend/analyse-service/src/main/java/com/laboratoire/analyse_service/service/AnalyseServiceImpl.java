package com.laboratoire.analyse_service.service;

import com.laboratoire.analyse_service.feign.LaboratoireInterface;
import com.laboratoire.analyse_service.model.Analyse;
import com.laboratoire.analyse_service.repository.AnalyseRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String getAnalyseByLaboratoire(Long laboratoireId) {
        return laboratoireInterface.getLaboratoireNameById(laboratoireId);
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
    public boolean deleteAnalyse(Long id) {
        if (analyseRepository.existsById(id)) {
            analyseRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
