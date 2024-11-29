package com.laboratoire.examen_service.repository;

import com.laboratoire.examen_service.model.Examen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamenRepository extends JpaRepository<Examen, Long> {
}
