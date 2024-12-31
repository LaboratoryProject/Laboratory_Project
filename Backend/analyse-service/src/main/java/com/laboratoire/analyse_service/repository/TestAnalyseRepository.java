package com.laboratoire.analyse_service.repository;

import com.laboratoire.analyse_service.model.TestAnalyse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface TestAnalyseRepository extends JpaRepository<TestAnalyse, Integer> {
    // Tu peux ajouter des méthodes de recherche personnalisées ici si besoin
}
