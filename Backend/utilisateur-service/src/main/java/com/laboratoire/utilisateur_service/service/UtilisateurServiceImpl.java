package com.laboratoire.utilisateur_service.service;

import com.laboratoire.utilisateur_service.controller.ApiResponse;
import com.laboratoire.utilisateur_service.dto.AdminUtilisateurDTO;
import com.laboratoire.utilisateur_service.dto.LaboratoireDTO;
import com.laboratoire.utilisateur_service.dto.UtilisateurDTO;
import com.laboratoire.utilisateur_service.feign.LaboratoireClient;
import com.laboratoire.utilisateur_service.model.Utilisateur;
import com.laboratoire.utilisateur_service.model.Role;
import com.laboratoire.utilisateur_service.repository.UtilisateurRepository;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.keycloak.admin.client.resource.GroupsResource;
import org.keycloak.admin.client.resource.UsersResource;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private static final Logger log = LoggerFactory.getLogger(UtilisateurServiceImpl.class);
    @Autowired
    private UtilisateurRepository utilisateurRepository;


    @Autowired
    private GroupsResource groupsResource;

    @Autowired
    private UsersResource usersResource;

    @Autowired
    private LaboratoireClient laboratoireClient;

    /**
     * Récupère tous les utilisateurs admin avec leurs détails de laboratoire
     * @return Liste des utilisateurs admin avec informations de laboratoire
     */
    public List<AdminUtilisateurDTO> getAllAdminUtilisateurs() {
        List<Utilisateur> adminUsers = utilisateurRepository.findByRole(Role.ADMIN);
        //utilisateurs li 3endhom role ADMIN

        return adminUsers.stream().map(utilisateur -> {
            AdminUtilisateurDTO dto = new AdminUtilisateurDTO();
            // Mapper les propriétés de l'utilisateur
            dto.setId(utilisateur.getId());
            dto.setEmail(utilisateur.getEmail());
            dto.setNomComplet(utilisateur.getNomComplet());
            dto.setProfession(utilisateur.getProfession());
            dto.setNumTel(utilisateur.getNumTel());
            dto.setRole(utilisateur.getRole());

            // Récupérer les informations du laboratoire
            try {
                System.out.println("one");
                LaboratoireDTO laboratoire = laboratoireClient.getLaboratoireDTOById(utilisateur.getFkIdLaboratoire());
                System.out.println("two");
                dto.setLaboratoireNom(laboratoire.getNom());
                dto.setLaboratoireNrc(laboratoire.getNrc());
                System.out.println(laboratoire.getId());
                System.out.println(laboratoire.getNrc());
                System.out.println(laboratoire.getNom());
                System.out.println("three");
                dto.getLaboratoireNom();
                System.out.println(dto.getLaboratoireNrc());
            } catch (Exception e) {
                // Gérer l'erreur si la récupération du laboratoire échoue
                // Vous pouvez logger l'erreur ou la gérer selon vos besoins
            }

            return dto;
        }).collect(Collectors.toList());
    }

    /**
     * Récupère un utilisateur admin spécifique avec ses détails de laboratoire
     * @param id Identifiant de l'utilisateur
     * @return Utilisateur admin avec informations de laboratoire
     */
    public AdminUtilisateurDTO getAdminUtilisateurById(Long id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .filter(u -> u.getRole() == Role.ADMIN)
                .orElseThrow(() -> new RuntimeException("Utilisateur admin non trouvé avec l'ID: " + id));

        AdminUtilisateurDTO dto = new AdminUtilisateurDTO();
        // Mapper les propriétés de l'utilisateur
        dto.setId(utilisateur.getId());
        dto.setEmail(utilisateur.getEmail());
        dto.setNomComplet(utilisateur.getNomComplet());
        dto.setProfession(utilisateur.getProfession());
        dto.setNumTel(utilisateur.getNumTel());
        dto.setRole(utilisateur.getRole());

        // Récupérer les informations du laboratoire
        LaboratoireDTO laboratoire = laboratoireClient.getLaboratoireDTOById(utilisateur.getFkIdLaboratoire());
        dto.setLaboratoireDetails(laboratoire);

        return dto;
    }

    @Transactional
    public Utilisateur modifierAdministrateur(Long id, Utilisateur utilisateur) {
        Utilisateur utilisateurExistant = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrateur introuvable"));
        utilisateurExistant.setNomComplet(utilisateur.getNomComplet());
        utilisateurExistant.setEmail(utilisateur.getEmail());
        utilisateurExistant.setProfession(utilisateur.getProfession());
        utilisateurExistant.setNumTel(utilisateur.getNumTel());
        utilisateurExistant.setSignature(utilisateur.getSignature());
        utilisateurExistant.setRole(utilisateur.getRole());
        utilisateurExistant.setPassword(utilisateur.getPassword());
        return utilisateurRepository.save(utilisateurExistant);
    }

    // Méthode pour supprimer un administrateur
    @Transactional
    public void supprimerAdministrateur(Long id) {
        Utilisateur utilisateurExistant = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrateur introuvable"));
        if (utilisateurExistant.getRole() == Role.ADMIN) {
            utilisateurRepository.delete(utilisateurExistant);
        } else {
            throw new RuntimeException("Seuls les administrateurs peuvent être supprimés");
        }
    }

    // Method to modify an admin user by deleting the existing one and creating a new one
    public Utilisateur modifyUtilisateur(Utilisateur utilisateur) {
        // Step 1: Delete the existing admin user with the same laboratory ID and role
        utilisateurRepository.deleteByFkIdLaboratoireAndRole(utilisateur.getFkIdLaboratoire(), Role.ADMIN);

        // Step 2: Create and save the new user
        return utilisateurRepository.save(utilisateur);
    }


    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }
    public Utilisateur getUtilisateurById(Long id) {
        return utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur not found with id: " + id));
    }
    public Utilisateur getUserByKeycloakId(String keycloakId) {
        Utilisateur utilisateur = utilisateurRepository.findByKeycloakId(keycloakId).orElse(null);
        if (utilisateur != null) {
            return utilisateur;
        }
        return null;
    }

    public ApiResponse createUtilisateur(UtilisateurDTO dto) throws IOException {
        System.out.println("ah");
        System.out.println(dto.getNrc());
        System.out.println(dto.getEmail());
        Long laboratoireId = laboratoireClient.findIdByNrc(dto.getNrc()).getBody();
        System.out.println(laboratoireId);
        Utilisateur utilisateurDTO = new Utilisateur(dto);
        utilisateurDTO.setFkIdLaboratoire(laboratoireId);
        // Vérification des champs obligatoires
        if (utilisateurDTO.getEmail() == null || utilisateurDTO.getPassword() == null) {
            return new ApiResponse(400, "Email et mot de passe sont requis");
        }

        try {
            // Création de l'utilisateur dans Keycloak
            UserRepresentation user = new UserRepresentation();
            user.setUsername(utilisateurDTO.getEmail());
            user.setEmail(utilisateurDTO.getEmail());
            user.setFirstName(utilisateurDTO.getNomComplet());
            user.setLastName(utilisateurDTO.getNomComplet());
            user.setEnabled(true);

            CredentialRepresentation credential = new CredentialRepresentation();
            credential.setTemporary(false);
            credential.setType(CredentialRepresentation.PASSWORD);
            credential.setValue(utilisateurDTO.getPassword());
            user.setCredentials(Collections.singletonList(credential));

            Response response = usersResource.create(user);
            int status = response.getStatus();
            log.info("User creation response status: {}", status);

            if (status != 201) {
                throw new IOException("Erreur lors de la création de l'utilisateur dans Keycloak");
            }

            // Récupération de l'ID utilisateur de Keycloak
            String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
            log.info("Extracted Keycloak user ID: {}", userId);

            log.info("le role from le user : {}", utilisateurDTO.getRole());


            // Afficher tous les groupes disponibles dans Keycloak pour diagnostic
            List<GroupRepresentation> groups = groupsResource.groups();
            log.info("Liste des groupes dans le realm: {}", groups.stream()
                    .map(GroupRepresentation::getName)
                    .collect(Collectors.toList()));

            String roleName = utilisateurDTO.getRole().name();

            // Trouver le groupe Keycloak correspondant au rôle
            GroupRepresentation group = groupsResource.groups().stream()
                    .filter(g -> g.getName().equals(roleName))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Group not found in Keycloak"));


            // Ajouter l'utilisateur au groupe Keycloak
            usersResource.get(userId).joinGroup(group.getId());
            log.info("User added to Keycloak group: {}", group.getName());

            // Création de l'utilisateur dans la base de données locale

            utilisateurDTO.setKeycloakId(userId);



            // Sauvegarder l'utilisateur dans la base de données locale
            utilisateurRepository.save(utilisateurDTO);
            log.info("User successfully saved in the local database with ID: {}", utilisateurDTO.getKeycloakId());

            return new ApiResponse(201, "Utilisateur créé avec succès");
        } catch (Exception e) {
            log.error("Exception pendant la création de l'utilisateur: ", e);
            throw new IOException("Erreur lors de la création de l'utilisateur", e);
        }
    }



    /**
     * Récupère un utilisateur admin par son ID
     * @param id Identifiant de l'utilisateur
     * @return Utilisateur admin
     */


    /**
     * Compte le nombre total d'utilisateurs admin
     * @return Nombre d'utilisateurs admin
     */
    public long countAdminUtilisateurs() {
        return utilisateurRepository.findByRole(Role.ADMIN).size();
    }

    public ApiResponse updateUtilisateur(Long utilisateurId, Utilisateur utilisateurDTO) {
        // Vérification des champs obligatoires
        if (utilisateurDTO.getEmail() == null || utilisateurDTO.getPassword() == null) {
            return new ApiResponse(400, "Email et mot de passe sont requis");
        }

        try {
            // Vérifier si l'utilisateur existe dans la base de données locale
            Optional<Utilisateur> utilisateurExist = utilisateurRepository.findById(utilisateurId);
            if (!utilisateurExist.isPresent()) {
                return new ApiResponse(404, "Utilisateur non trouvé");
            }

            Utilisateur utilisateurLocal = utilisateurExist.get();

            // Mise à jour des informations utilisateur dans Keycloak
            UserRepresentation user = usersResource.get(utilisateurLocal.getKeycloakId()).toRepresentation();
            user.setEmail(utilisateurDTO.getEmail());
            user.setFirstName(utilisateurDTO.getNomComplet());
            user.setLastName(utilisateurDTO.getNomComplet());

            // Si le mot de passe a changé, on met à jour les credentials
            if (!utilisateurDTO.getPassword().equals(utilisateurLocal.getPassword())) {
                CredentialRepresentation credential = new CredentialRepresentation();
                credential.setTemporary(false);
                credential.setType(CredentialRepresentation.PASSWORD);
                credential.setValue(utilisateurDTO.getPassword());
                user.setCredentials(Collections.singletonList(credential));
            }

            // Mise à jour dans Keycloak
            usersResource.get(utilisateurLocal.getKeycloakId()).update(user);
            log.info("Utilisateur mis à jour dans Keycloak avec ID: {}", utilisateurDTO.getKeycloakId());

            // Mise à jour des rôles dans Keycloak
            String newRoleName = utilisateurDTO.getRole().name();
            List<GroupRepresentation> groups = groupsResource.groups();

            // Trouver le groupe Keycloak correspondant au rôle
            GroupRepresentation newGroup = groups.stream()
                    .filter(g -> g.getName().equals(newRoleName))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Group not found in Keycloak"));

            // Retirer l'utilisateur des anciens groupes
            List<GroupRepresentation> userGroups = usersResource.get(utilisateurLocal.getKeycloakId()).groups();
            for (GroupRepresentation oldGroup : userGroups) {
                usersResource.get(utilisateurLocal.getKeycloakId()).leaveGroup(oldGroup.getId());
            }
            log.info("Utilisateur retiré des anciens groupes");

            // Ajouter l'utilisateur au nouveau groupe
            usersResource.get(utilisateurLocal.getKeycloakId()).joinGroup(newGroup.getId());
            log.info("Utilisateur ajouté au groupe Keycloak: {}", newGroup.getName());

            // Mise à jour de l'utilisateur dans la base de données locale
            utilisateurLocal.setEmail(utilisateurDTO.getEmail());
            utilisateurLocal.setNomComplet(utilisateurDTO.getNomComplet());
            utilisateurLocal.setPassword(utilisateurDTO.getPassword());
            utilisateurLocal.setRole(utilisateurDTO.getRole());
            utilisateurRepository.save(utilisateurLocal);
            log.info("Utilisateur mis à jour dans la base de données locale");

            return new ApiResponse(200, "Utilisateur mis à jour avec succès");

        } catch (Exception e) {
            log.error("Exception pendant la mise à jour de l'utilisateur: ", e);

        }
        return new ApiResponse(200, "Utilisateur mis à jour avec succès");
    }


    public ApiResponse deleteUtilisateur(Long utilisateurId) {
        try {
            // Vérifier si l'utilisateur existe dans la base de données locale
            Optional<Utilisateur> utilisateurExist = utilisateurRepository.findById(utilisateurId);
            if (!utilisateurExist.isPresent()) {
                return new ApiResponse(404, "Utilisateur non trouvé");
            }

            Utilisateur utilisateurLocal = utilisateurExist.get();

            // Suppression de l'utilisateur dans Keycloak
            usersResource.get(utilisateurLocal.getKeycloakId()).remove();
            log.info("Utilisateur supprimé de Keycloak avec ID: {}", utilisateurLocal.getKeycloakId());

            // Suppression de l'utilisateur dans la base de données locale
            utilisateurRepository.delete(utilisateurLocal);
            log.info("Utilisateur supprimé de la base de données locale");



        } catch (Exception e) {
            log.error("Exception pendant la suppression de l'utilisateur: ", e);

        }
        return new ApiResponse(200, "Utilisateur supprimé avec succès");
    }



    public List<Utilisateur> getUtilisateursByLaboratoire(Long laboratoireId) {
        return utilisateurRepository.findByFkIdLaboratoire(laboratoireId);
    }

    public List<Utilisateur> getUtilisateursByRole(Role role) {
        return utilisateurRepository.findByRole(role);
    }
}