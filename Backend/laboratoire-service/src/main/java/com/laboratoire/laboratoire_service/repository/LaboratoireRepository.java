package com.laboratoire.laboratoire_service.repository;

import com.laboratoire.laboratoire_service.model.Laboratoire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LaboratoireRepository extends JpaRepository<Laboratoire,Long> {

    @Query("SELECT l.id FROM Laboratoire l WHERE l.nrc = :nrc")
    Long findIdLaboratoireByNrc(@Param("nrc") String nrc);
}
