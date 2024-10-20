package com.laboratoire.laboratoire_service.service;

import com.laboratoire.laboratoire_service.dto.LaboratoireRequest;
import com.laboratoire.laboratoire_service.dto.LaboratoireResponse;
import com.laboratoire.laboratoire_service.model.Laboratoire;

import java.util.List;

public interface LaboratoireService {

    void createLaboratoire(LaboratoireRequest laboratoireRequest) ;
    List<LaboratoireResponse> getAllLaboratoires() ;
    LaboratoireResponse mapToLaboratoireResponse(Laboratoire laboratoire) ;
}
