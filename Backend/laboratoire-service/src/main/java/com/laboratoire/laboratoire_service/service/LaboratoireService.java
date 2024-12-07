package com.laboratoire.laboratoire_service.service;

import com.laboratoire.laboratoire_service.dto.LaboratoireRequest;
import com.laboratoire.laboratoire_service.dto.LaboratoireResponse;
import com.laboratoire.laboratoire_service.model.Laboratoire;

import java.util.List;

public interface LaboratoireService {

    LaboratoireResponse createLaboratoire(LaboratoireRequest laboratoireRequest) ;
    List<Laboratoire> getAllLaboratoires() ;
    Laboratoire mapToLaboratoireResponse(Laboratoire laboratoire) ;
    LaboratoireResponse getLaboratoireById(Long id);
    String getLaboratoireNameById(Long id);
}
