package com.laboratoire.analyse_service.service;
import com.laboratoire.analyse_service.model.Analyse;
import java.util.List;
import java.util.Optional;


public interface AnalyseService {
    List<Analyse> getAllAnalyses();
    Optional<Analyse> getAnalyseById(Long id);
    Analyse saveAnalyse(Analyse analyse);
    void deleteAnalyse(Long id);
    List<Analyse> getAnalysesByLaboratoire(Long laboratoireId);
}