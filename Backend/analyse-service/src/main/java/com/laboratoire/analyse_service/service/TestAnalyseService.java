package com.laboratoire.analyse_service.service;

import com.laboratoire.analyse_service.model.TestAnalyse;

import java.util.List;
import java.util.Optional;

public interface TestAnalyseService {
    TestAnalyse ajouterTestAnalyse(TestAnalyse testAnalyse);
    List<TestAnalyse> getAllTestAnalyses();
    Optional<TestAnalyse> getTestAnalyseById(int id);
    boolean supprimerTestAnalyse(int id);
}
