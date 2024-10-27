package com.laboratoire.utilisateur_service.controller;

import com.laboratoire.utilisateur_service.model.Role;
import com.laboratoire.utilisateur_service.model.Utilisateur;
import com.laboratoire.utilisateur_service.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {
    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurService.getAllUtilisateurs();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Utilisateur getUtilisateurById(@PathVariable Long id) {
        return utilisateurService.getUtilisateurById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Utilisateur createUtilisateur(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.createUtilisateur(utilisateur);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Utilisateur updateUtilisateur(@PathVariable Long id,  @RequestBody Utilisateur utilisateur) {
        return utilisateurService.updateUtilisateur(id, utilisateur);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUtilisateur(@PathVariable Long id) {
        utilisateurService.deleteUtilisateur(id);
    }

    @GetMapping("/laboratoire/{laboratoireId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Utilisateur> getUtilisateursByLaboratoire(@PathVariable Long laboratoireId) {
        return utilisateurService.getUtilisateursByLaboratoire(laboratoireId);
    }

    @GetMapping("/role/{role}")
    @ResponseStatus(HttpStatus.OK)
    public List<Utilisateur> getUtilisateursByRole(@PathVariable Role role) {
        return utilisateurService.getUtilisateursByRole(role);
    }
}
