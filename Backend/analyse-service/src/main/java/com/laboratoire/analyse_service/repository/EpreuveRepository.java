package com.laboratoire.analyse_service.repository;

import com.laboratoire.analyse_service.model.Epreuve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpreuveRepository extends JpaRepository<Epreuve, Integer> {
    // Tu peux ajouter des méthodes de recherche personnalisées ici si besoin
}
