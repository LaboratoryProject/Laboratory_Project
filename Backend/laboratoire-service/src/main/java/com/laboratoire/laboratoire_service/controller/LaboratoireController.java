package com.laboratoire.laboratoire_service.controller;


import com.laboratoire.laboratoire_service.dto.LaboratoireRequest;
import com.laboratoire.laboratoire_service.dto.LaboratoireResponse;
import com.laboratoire.laboratoire_service.service.LaboratoireService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/laboratoires")
public class LaboratoireController {

    private final LaboratoireService laboratoireService;

    private LaboratoireController (LaboratoireService laboratoireService){
        this.laboratoireService=laboratoireService;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createLaboratoire(@RequestBody LaboratoireRequest laboratoireRequest) {
        laboratoireService.createLaboratoire(laboratoireRequest);
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LaboratoireResponse getLaboratoireById(@PathVariable Long id) {
        return laboratoireService.getLaboratoireById(id);
    }
    @GetMapping("/nom/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String getLaboratoireNameById(@PathVariable Long id) {
        return laboratoireService.getLaboratoireNameById(id);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LaboratoireResponse> getAllLaboratoires() {
        return laboratoireService.getAllLaboratoires();
    }

}