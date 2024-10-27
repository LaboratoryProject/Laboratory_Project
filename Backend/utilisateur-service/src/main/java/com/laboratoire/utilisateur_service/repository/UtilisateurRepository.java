package com.laboratoire.utilisateur_service.repository;

import com.laboratoire.utilisateur_service.model.Role;
import com.laboratoire.utilisateur_service.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// UtilisateurRepository.java
@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findByEmail(String email);
    List<Utilisateur> findByFkIdLaboratoire(Long laboratoireId);
    List<Utilisateur> findByRole(Role role);
}
