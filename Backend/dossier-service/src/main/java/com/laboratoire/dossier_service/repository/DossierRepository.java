package com.laboratoire.dossier_service.repository;

import com.laboratoire.dossier_service.model.Dossier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DossierRepository extends JpaRepository<Dossier, Long> {
    Dossier findByFkEmailUtilisateur(String fkEmailUtilisateur);
    Optional<Dossier> findByNumDossier(String numDossier);  // Recherche par numDossier
}
