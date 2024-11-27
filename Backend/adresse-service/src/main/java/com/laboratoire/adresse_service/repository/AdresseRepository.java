package com.laboratoire.adresse_service.repository;

import com.laboratoire.adresse_service.model.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdresseRepository extends JpaRepository<Adresse, Long> {
    // Méthodes supplémentaires de recherche, si nécessaire
    Adresse findByCodePostal(String codePostal);
}
