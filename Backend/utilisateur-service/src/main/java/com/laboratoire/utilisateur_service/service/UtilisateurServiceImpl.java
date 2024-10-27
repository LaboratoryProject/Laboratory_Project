package com.laboratoire.utilisateur_service.service;

import com.laboratoire.utilisateur_service.model.Utilisateur;
import com.laboratoire.utilisateur_service.model.Role;
import com.laboratoire.utilisateur_service.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }
    public Utilisateur getUtilisateurById(Long id) {
        return utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur not found with id: " + id));
    }

    public Utilisateur createUtilisateur(Utilisateur utilisateur) {
        // Check if email already exists
        if (utilisateurRepository.findByEmail(utilisateur.getEmail()).isPresent()) {
           // throw new ResourceAlreadyExistsException("Email already exists: " + utilisateur.getEmail());
        }
        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur updateUtilisateur(Long id, Utilisateur utilisateurDetails) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur not found with id: " + id));

        // Check if new email already exists for another user
        if (!utilisateur.getEmail().equals(utilisateurDetails.getEmail()) &&
                utilisateurRepository.findByEmail(utilisateurDetails.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists: " + utilisateurDetails.getEmail());
        }

        utilisateur.setEmail(utilisateurDetails.getEmail());
        utilisateur.setFkIdLaboratoire(utilisateurDetails.getFkIdLaboratoire());
        utilisateur.setNomComplet(utilisateurDetails.getNomComplet());
        utilisateur.setProfession(utilisateurDetails.getProfession());
        utilisateur.setNumTel(utilisateurDetails.getNumTel());
        utilisateur.setSignature(utilisateurDetails.getSignature());
        utilisateur.setRole(utilisateurDetails.getRole());

        return utilisateurRepository.save(utilisateur);
    }

    public void deleteUtilisateur(Long id) {
        if (!utilisateurRepository.existsById(id)) {
            throw new RuntimeException("Utilisateur not found with id: " + id);
        }
        utilisateurRepository.deleteById(id);
    }


    public List<Utilisateur> getUtilisateursByLaboratoire(Long laboratoireId) {
        return utilisateurRepository.findByFkIdLaboratoire(laboratoireId);
    }

    public List<Utilisateur> getUtilisateursByRole(Role role) {
        return utilisateurRepository.findByRole(role);
    }
}