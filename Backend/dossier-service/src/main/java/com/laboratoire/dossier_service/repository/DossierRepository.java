package com.laboratoire.dossier_service.repository;

import com.laboratoire.dossier_service.model.Dossier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DossierRepository extends JpaRepository<Dossier, Long> {

    // Recherche par email utilisateur
    Optional<Dossier> findByFkEmailUtilisateur(String fkEmailUtilisateur);

    // Recherche par numéro de dossier
    Optional<Dossier> findByNumDossier(Long numDossier);
}
