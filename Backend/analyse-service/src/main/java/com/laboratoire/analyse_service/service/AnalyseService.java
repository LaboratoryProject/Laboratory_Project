package com.laboratoire.analyse_service.service;

import com.laboratoire.analyse_service.model.Analyse;

import java.util.List;
import java.util.Optional;

public interface AnalyseService {
    List<Analyse> getAllAnalyses();
    String getAnalyseByLaboratoire(Long laboratoireId);
    Optional<Analyse> getAnalyseById(Long id);
    Analyse saveAnalyse(Analyse analyse);
    boolean deleteAnalyse(Long id);
}
