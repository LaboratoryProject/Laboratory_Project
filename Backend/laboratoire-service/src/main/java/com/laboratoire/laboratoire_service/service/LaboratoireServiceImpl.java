package com.laboratoire.laboratoire_service.service;

import com.laboratoire.laboratoire_service.dto.LaboratoireRequest;
import com.laboratoire.laboratoire_service.dto.LaboratoireResponse;

import com.laboratoire.laboratoire_service.model.Laboratoire;
import com.laboratoire.laboratoire_service.repository.LaboratoireRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaboratoireServiceImpl implements LaboratoireService {


    private static final Logger log = LoggerFactory.getLogger(LaboratoireServiceImpl.class);
    private final LaboratoireRepository laboratoireRepository;

    public LaboratoireServiceImpl(LaboratoireRepository laboratoireRepository) {
        this.laboratoireRepository = laboratoireRepository;
    }

    // Method to create a laboratory
    public void createLaboratoire(LaboratoireRequest laboratoireRequest) {
        Laboratoire laboratoire = new Laboratoire(
                laboratoireRequest.nom(),
                laboratoireRequest.logo(),
                laboratoireRequest.nrc(),
                laboratoireRequest.active(),
                laboratoireRequest.dateActivation()
        );

        laboratoireRepository.save(laboratoire);
        log.info("Laboratoire {} is saved", laboratoire.getId());
    }

    // Method to get all laboratories
    public List<LaboratoireResponse> getAllLaboratoires() {
        List<Laboratoire> laboratoires = laboratoireRepository.findAll();

        return laboratoires.stream().map(this::mapToLaboratoireResponse).toList();
    }

    // Helper method to map entity to response DTO
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
}
