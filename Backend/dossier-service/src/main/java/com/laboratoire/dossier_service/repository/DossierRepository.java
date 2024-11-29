package com.laboratoire.dossier_service.repository;

import com.laboratoire.dossier_service.model.Dossier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DossierRepository extends JpaRepository<Dossier, Long> {
    Dossier findByFkEmailUtilisateur(String fkEmailUtilisateur);
}
