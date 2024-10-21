package com.laboratoire.analyse_service.repository;


import com.laboratoire.analyse_service.model.Analyse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnalyseRepository extends JpaRepository<Analyse, Long> {
    List<Analyse> findByFkIdLaboratoire(Long laboratoireId);
}