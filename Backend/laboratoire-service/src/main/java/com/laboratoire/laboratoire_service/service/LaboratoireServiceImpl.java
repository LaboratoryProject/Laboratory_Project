package com.laboratoire.laboratoire_service.service;

import com.laboratoire.laboratoire_service.dto.*;
import com.laboratoire.laboratoire_service.model.Laboratoire;
import com.laboratoire.laboratoire_service.repository.LaboratoireRepository;
import com.laboratoire.laboratoire_service.feign.AdresseClient;
import com.laboratoire.laboratoire_service.feign.ContactClient;
import io.minio.MinioClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;


@Service
public class LaboratoireServiceImpl implements LaboratoireService {
    private static final Logger log = LoggerFactory.getLogger(LaboratoireServiceImpl.class);

    private final LaboratoireRepository laboratoireRepository;
    private final AdresseClient adresseClient;
    private final ContactClient contactClient;
    private final MinioService minioService;
    private final MinioClient minioClient;
    private final String defaultBucket;


    public LaboratoireServiceImpl(
            LaboratoireRepository laboratoireRepository,
            AdresseClient adresseClient,
            ContactClient contactClient, MinioService minioService, MinioClient minioClient,  @Value("${minio.bucket}") String defaultBucket
    ) {
        this.laboratoireRepository = laboratoireRepository;
        this.adresseClient = adresseClient;
        this.contactClient = contactClient;
        this.minioService = minioService;
        this.minioClient = minioClient;
        this.defaultBucket = defaultBucket;
    }

    // Méthode de création de laboratoire avec gestion des services externes
    /*@Transactional
    public LaboratoireResponse createLaboratoire(LaboratoireRequest laboratoireRequest) {
        // Créer l'entité Laboratoire
        Laboratoire laboratoire = new Laboratoire(
                laboratoireRequest.nom(),
                laboratoireRequest.logo(),
                laboratoireRequest.nrc(),
                laboratoireRequest.active(),
                laboratoireRequest.dateActivation()
        );

        // Sauvegarder le laboratoire
        Laboratoire savedLaboratoire = laboratoireRepository.save(laboratoire);
        log.info("Laboratoire {} is saved", savedLaboratoire.getId());

        // Mapper et retourner la réponse
        return mapToLaboratoireResponse(savedLaboratoire);
    }
*/
    // Méthode de création de laboratoire complet (avec adresse et contact)
    @Transactional
    public Laboratoire createLaboratoireComplet(LaboratoireCompletDTO dto) throws IOException {
        // Validation des données d'entrée
        if (dto == null || dto.getLaboratoire() == null) {
            throw new IllegalArgumentException("Les informations du laboratoire sont obligatoires");
        }

        // Gestion du téléchargement du logo
        String logoFileName = null;
        if (dto.getLaboratoire().getLogo() != null && !dto.getLaboratoire().getLogo().isEmpty()) {
            try {
                logoFileName = minioService.uploadFile(
                        dto.getLaboratoire().getLogo(),
                        dto.getLaboratoire().getLogo().getOriginalFilename()
                );
            } catch (Exception e) {
                log.error("Échec du téléchargement du logo", e);
                throw new IOException("Impossible de télécharger le logo", e);
            }
        }

        // Création de l'entité Laboratoire
        Laboratoire laboratoire = new Laboratoire(
                dto.getLaboratoire().getNom(),
                logoFileName,  // Stocker le nom du fichier
                dto.getLaboratoire().getNrc(),
                dto.getLaboratoire().isActive(),
                dto.getLaboratoire().getDateActivation()
        );

        // Sauvegarde du laboratoire
        Laboratoire savedLaboratoire = laboratoireRepository.save(laboratoire);
        log.info("Laboratoire créé avec succès - ID: {}, Nom: {}", savedLaboratoire.getId(), savedLaboratoire.getNom());

        // Vérification et création de l'adresse
        if (dto.getAdresse() == null) {
            throw new IllegalArgumentException("Les informations d'adresse sont obligatoires");
        }
        AdresseDTO adresseDto = dto.getAdresse();
        Long adresseId = adresseClient.creerAdresse(adresseDto).getBody().getId();
        log.info("Adresse créée avec succès - ID: {}", adresseId);

        // Vérification et création du contact
        if (dto.getContactLaboratoire() == null) {
            throw new IllegalArgumentException("Les informations de contact sont obligatoires");
        }
        ContactLaboratoireDTO contactDto = dto.getContactLaboratoire();
        contactDto.setFkIdLaboratoire(savedLaboratoire.getId());
        contactDto.setFkIdAdresse(adresseId);
        Long contactId = contactClient.createContact(contactDto).getBody().getId();
        log.info("Contact créé avec succès - ID: {}", contactId);

        return mapToLaboratoireResponse(savedLaboratoire);
    }

    /*
    @Transactional
    public LaboratoireResponse updateLaboratoireComplet(Long id, LaboratoireCompletDTO dto) {
        // Rechercher le laboratoire existant
        Laboratoire existingLaboratoire = laboratoireRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Laboratoire not found with id: " + id));

        // Mettre à jour les propriétés du laboratoire
        existingLaboratoire.setNom(dto.getLaboratoire().getNom());
        existingLaboratoire.setLogo(dto.getLaboratoire().getLogo());
        existingLaboratoire.setNrc(dto.getLaboratoire().getNrc());
        existingLaboratoire.setActive(dto.getLaboratoire().isActive());
        existingLaboratoire.setDateActivation(dto.getLaboratoire().getDateActivation());

        // Sauvegarder les modifications du laboratoire
        Laboratoire updatedLaboratoire = laboratoireRepository.save(existingLaboratoire);
        log.info("Laboratoire {} is updated", updatedLaboratoire.getId());

        // Mettre à jour l'adresse via le client Feign
        if (dto.getAdresse() != null) {
            AdresseDTO adresseDto = dto.getAdresse();
            // Vous devrez ajouter une méthode de mise à jour dans votre AdresseClient
            // Par exemple :
             Long adresseId = adresseClient.updateAdresse(adresseDto).getBody().getId();
            log.info("Adresse update process needed");
        }

        // Mettre à jour le contact via le client Feign
        if (dto.getContactLaboratoire() != null) {
            ContactLaboratoireDTO contactDto = dto.getContactLaboratoire();
            contactDto.setFkIdLaboratoire(updatedLaboratoire.getId());

            // Vous devrez ajouter une méthode de mise à jour dans votre ContactClient
            // Par exemple :
             Long contactId = contactClient.updateContact(contactDto).getBody().getId();
            log.info("Contact update process needed");
        }

        return mapToLaboratoireResponse(updatedLaboratoire);
    }
    */
    @Transactional
    public void deleteLaboratoireComplet(Long id) {
        // Rechercher le laboratoire existant
        Laboratoire laboratoire = laboratoireRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Laboratoire not found with id: " + id));

        try {
            // Supprimer les contacts associés via le client Feign
            try {
                contactClient.deleteContactsByLaboratoireId(id);
                log.info("Contacts for Laboratoire {} deleted", id);
            } catch (Exception e) {
                log.error("Error deleting contacts for Laboratoire {}: {}", id, e.getMessage());
                // Vous pouvez choisir de propager l'exception ou de la gérer différemment
            }

            // Supprimer les adresses associées via le client Feign
            try {
                adresseClient.deleteAdressesByLaboratoireId(id);
                log.info("Adresses for Laboratoire {} deleted", id);
            } catch (Exception e) {
                log.error("Error deleting adresses for Laboratoire {}: {}", id, e.getMessage());
                // Vous pouvez choisir de propager l'exception ou de la gérer différemment
            }

            // Supprimer le laboratoire
            laboratoireRepository.delete(laboratoire);
            log.info("Laboratoire {} is deleted", id);

        } catch (Exception e) {
            log.error("Error deleting Laboratoire {}: {}", id, e.getMessage());
            throw new RuntimeException("Failed to delete Laboratoire and associated records", e);
        }
    }

    @Override
    public LaboratoireResponse createLaboratoire(LaboratoireRequest laboratoireRequest) {
        return null;
    }

    @Override
    public List<LaboratoireResponse> getAllLaboratoires() {
        return List.of();
    }

    // Méthode pour récupérer tous les laboratoires
  /*  public List<LaboratoireResponse> getAllLaboratoires() {
        List<Laboratoire> laboratoires = laboratoireRepository.findAll();
        return laboratoires.stream()
                .map(this::mapToLaboratoireResponse)
                .toList();
    }

    // Méthode pour récupérer un laboratoire par ID
    public LaboratoireResponse getLaboratoireById(Long id) {
        return laboratoireRepository.findById(id)
                .map(this::mapToLaboratoireResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Laboratoire not found with id: " + id));
    }

    // Méthode pour récupérer le nom d'un laboratoire
    public String getLaboratoireNameById(Long id) {
        return laboratoireRepository.findById(id)
                .map(Laboratoire::getNom)
                .orElseThrow(() -> new ResourceNotFoundException("Laboratory not found with id: " + id));
    }
*/
    // Méthode de mapping Laboratoire vers LaboratoireResponse
    public Laboratoire mapToLaboratoireResponse(Laboratoire laboratoire) {
        Laboratoire response = new Laboratoire();


        response.setId(laboratoire.getId());
        response.setNom(laboratoire.getNom());
        response.setNrc(laboratoire.getNrc());
        response.setActive(laboratoire.isActive());
        response.setDateActivation(laboratoire.getDateActivation());


        if (laboratoire.getLogo() != null) {
            response.setLogo(generateMinioUrl(laboratoire.getLogo()));
        }

        return response;
    }

    @Override
    public LaboratoireResponse getLaboratoireById(Long id) {
        return null;
    }

    @Override
    public String getLaboratoireNameById(Long id) {
        return "";
    }
    private String generateMinioUrl(String fileName) {
        return minioService.getFileUrl(fileName);
    }

    // Exception personnalisée pour les ressources non trouvées
    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }
}