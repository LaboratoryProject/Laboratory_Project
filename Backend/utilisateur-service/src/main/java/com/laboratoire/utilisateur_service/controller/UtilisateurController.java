package com.laboratoire.utilisateur_service.controller;

import com.laboratoire.utilisateur_service.dto.AdminUtilisateurDTO;
import com.laboratoire.utilisateur_service.dto.UtilisateurDTO;
import com.laboratoire.utilisateur_service.model.Role;
import com.laboratoire.utilisateur_service.model.Utilisateur;
import com.laboratoire.utilisateur_service.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/utilisateurs")

@CrossOrigin(origins = "http://localhost:4200",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
public class UtilisateurController {
    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurService.getAllUtilisateurs();
    }

    @GetMapping ("/Admin")
    public ResponseEntity<List<AdminUtilisateurDTO>> getAllAdminUtilisateurs() {
        List<AdminUtilisateurDTO> adminUtilisateurs = utilisateurService.getAllAdminUtilisateurs();
        return ResponseEntity.ok(adminUtilisateurs);
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Utilisateur getUtilisateurById(@PathVariable Long id) {
        return utilisateurService.getUtilisateurById(id);
    }

    @PostMapping("/nn")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse createUtilisateur(@RequestBody UtilisateurDTO utilisateurDTO) throws IOException {
        return utilisateurService.createUtilisateur(utilisateurDTO);
    }

    @PutMapping("/admin/{id}")
    public Utilisateur modifierAdministrateur(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
        return utilisateurService.modifierAdministrateur(id, utilisateur);
    }

    @GetMapping("/keycloak/{keycloakId}")
    public ResponseEntity<Utilisateur> getUserByKeycloakId(@PathVariable String keycloakId) {
        Utilisateur utilisateurDTO = utilisateurService.getUserByKeycloakId(keycloakId);
        System.out.println(utilisateurDTO.getKeycloakId());
        if (utilisateurDTO != null) {
            return new ResponseEntity<>(utilisateurDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint pour supprimer un administrateur
    @DeleteMapping("/admin/{id}")
    public void supprimerAdministrateur(@PathVariable Long id) {
        utilisateurService.supprimerAdministrateur(id);
    }

    @PutMapping("/modifier")
    public ResponseEntity<Utilisateur> modifyUtilisateur(@RequestBody Utilisateur utilisateur) {
        Utilisateur modifiedUtilisateur = utilisateurService.modifyUtilisateur(utilisateur);
        return ResponseEntity.ok(modifiedUtilisateur);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createUser(@RequestBody UtilisateurDTO utilisateurDTO) {
        // Ajouter cette ligne pour déboguer les autorités de l'utilisateur authentifié
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        System.out.println("les autorisationnnnnnnnnnnnnnnnn");
        authorities.forEach(authority -> System.out.println(authority.getAuthority()));
        System.out.println("lfu9");
        System.out.println(utilisateurDTO.getNrc());
        System.out.println(utilisateurDTO.getPassword());
        System.out.println(utilisateurDTO.getNomComplet());

        try {
            ApiResponse response = utilisateurService.createUtilisateur(utilisateurDTO);
            HttpStatus status = response.getStatus() != null ? response.getStatus() : HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity<>(response, status);
        } catch (IOException e) {
            return new ResponseEntity<>(new ApiResponse(500, "Erreur lors de la création de l'utilisateur"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUtilisateur(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
        try {
            ApiResponse updatedUtilisateur = utilisateurService.updateUtilisateur(id, utilisateur);
            return ResponseEntity.ok(updatedUtilisateur);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(400, e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUtilisateur(@PathVariable Long id) {
        try {
            utilisateurService.deleteUtilisateur(id);
            return ResponseEntity.ok(new ApiResponse(200, "Utilisateur supprimé avec succès"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(400, e.getMessage()));
        }
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
