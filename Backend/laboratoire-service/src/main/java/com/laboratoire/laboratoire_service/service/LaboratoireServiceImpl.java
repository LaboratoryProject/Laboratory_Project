package com.laboratoire.laboratoire_service.service;

import com.laboratoire.laboratoire_service.dto.*;
import com.laboratoire.laboratoire_service.model.Laboratoire;
import com.laboratoire.laboratoire_service.repository.LaboratoireRepository;
import com.laboratoire.laboratoire_service.feign.AdresseClient;
import com.laboratoire.laboratoire_service.feign.ContactClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LaboratoireServiceImpl implements LaboratoireService {
    private static final Logger log = LoggerFactory.getLogger(LaboratoireServiceImpl.class);

    private final LaboratoireRepository laboratoireRepository;
    private final AdresseClient adresseClient;
    private final ContactClient contactClient;


    public LaboratoireServiceImpl(
            LaboratoireRepository laboratoireRepository,
            AdresseClient adresseClient,
            ContactClient contactClient
    ) {
        this.laboratoireRepository = laboratoireRepository;
        this.adresseClient = adresseClient;
        this.contactClient = contactClient;
    }

    // Méthode de création de laboratoire avec gestion des services externes
    @Transactional
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

    // Méthode de création de laboratoire complet (avec adresse et contact)
    @Transactional
    public LaboratoireResponse createLaboratoireComplet(LaboratoireCompletDTO dto) {
        // Créer le laboratoire
        System.out.println(dto.getLaboratoire().getNom());


        Laboratoire laboratoire = new Laboratoire(
                dto.getLaboratoire().getNom(),
                dto.getLaboratoire().getLogo(),
                dto.getLaboratoire().getNrc(),
                dto.getLaboratoire().isActive(),
                dto.getLaboratoire().getDateActivation()
        );


        System.out.println(laboratoire.getNrc());

        Laboratoire savedLaboratoire = laboratoireRepository.save(laboratoire);
        System.out.println(savedLaboratoire.getId());
        System.out.println(savedLaboratoire.getNom());

        // Créer l'adresse via le client Feign
        AdresseDTO adresseDto = dto.getAdresse();

        Long adresseId = adresseClient.creerAdresse(adresseDto).getBody().getId();
        System.out.println(adresseDto);
        // Créer le contact via le client Feign
        ContactLaboratoireDTO contactDto = dto.getContactLaboratoire();
        contactDto.setFkIdLaboratoire(savedLaboratoire.getId());
        contactDto.setFkIdAdresse(adresseId);
        Long contactId = contactClient.createContact(contactDto).getBody().getId();

        log.info("Laboratoire complet créé avec ID: {}", savedLaboratoire.getId());

        return mapToLaboratoireResponse(savedLaboratoire);
    }

    // Méthode pour récupérer tous les laboratoires
    public List<LaboratoireResponse> getAllLaboratoires() {
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

    // Méthode de mapping Laboratoire vers LaboratoireResponse
    public LaboratoireResponse mapToLaboratoireResponse(Laboratoire laboratoire) {
        return new LaboratoireResponse(
                laboratoire.getId().toString(),
                laboratoire.getNom(),
                laboratoire.getLogo(),
                laboratoire.getNrc(),
                laboratoire.isActive(),
                laboratoire.getDateActivation()
        );
    }

    // Exception personnalisée pour les ressources non trouvées
    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }
}