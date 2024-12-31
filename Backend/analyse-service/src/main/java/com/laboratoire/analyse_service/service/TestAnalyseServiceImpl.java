package com.laboratoire.analyse_service.service;

import com.laboratoire.analyse_service.model.TestAnalyse;
import com.laboratoire.analyse_service.repository.TestAnalyseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestAnalyseServiceImpl implements TestAnalyseService {

    @Autowired
    private TestAnalyseRepository testAnalyseRepository;

    @Override
    public TestAnalyse ajouterTestAnalyse(TestAnalyse testAnalyse) {
        return testAnalyseRepository.save(testAnalyse);
    }

    @Override
    public List<TestAnalyse> getAllTestAnalyses() {
        return testAnalyseRepository.findAll();
    }

    @Override
    public Optional<TestAnalyse> getTestAnalyseById(int id) {
        return testAnalyseRepository.findById(id);
    }

    @Override
    public boolean supprimerTestAnalyse(int id) {
        if (testAnalyseRepository.existsById(id)) {
            testAnalyseRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
