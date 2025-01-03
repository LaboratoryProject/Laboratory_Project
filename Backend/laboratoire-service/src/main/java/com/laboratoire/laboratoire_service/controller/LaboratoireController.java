package com.laboratoire.laboratoire_service.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laboratoire.laboratoire_service.dto.LaboratoireCompletDTO;
import com.laboratoire.laboratoire_service.dto.LaboratoireDTO;
import com.laboratoire.laboratoire_service.dto.LaboratoireRequest;
import com.laboratoire.laboratoire_service.dto.LaboratoireResponse;
import com.laboratoire.laboratoire_service.model.Laboratoire;
import com.laboratoire.laboratoire_service.service.LaboratoireServiceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/laboratoires")
public class LaboratoireController {

    private final LaboratoireServiceImpl laboratoireService;

    public LaboratoireController(LaboratoireServiceImpl laboratoireService) {
        this.laboratoireService = laboratoireService;
    }

    @GetMapping("/findIdByNrc")
    public ResponseEntity<Long> findIdByNrc(@RequestParam String nrc) {
        Long id = laboratoireService.getIdByNrc(nrc);
        if (id != null) {
            return ResponseEntity.ok(id);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/update-partiel/{id}")
    public ResponseEntity<Laboratoire> updateLaboratoireParcellement(
            @PathVariable Long id,
            @RequestPart(value = "updates") String updates,
            @RequestPart(value = "logoFile", required = false) MultipartFile logoFile
    ) {
        try {
            // Parse JSON string into a Map
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> updatesMap = objectMapper.readValue(updates, new TypeReference<Map<String, Object>>() {});

            System.out.println("Received updates: " + updatesMap);

            // Special handling for nested "contactLaboratoire" field
            if (updatesMap.containsKey("contactLaboratoire")) {
                Map<String, Object> contactMap = (Map<String, Object>) updatesMap.get("contactLaboratoire");
                System.out.println("Received contactLaboratoire: " + contactMap);

            }

            // Pass the updates to the service layer
            Laboratoire updatedLaboratoire = laboratoireService.updateLaboratoireParcellement(id, updatesMap, logoFile);

            System.out.println("Updated laboratoire: " + updatedLaboratoire);
            return ResponseEntity.ok(updatedLaboratoire);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }




    @PostMapping
    public ResponseEntity<LaboratoireResponse> createSimpleLaboratoire(
            @Valid @RequestBody LaboratoireRequest laboratoireRequest) {
        LaboratoireResponse response = laboratoireService.createLaboratoire(laboratoireRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping(value = "/complet", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Laboratoire> createCompletLaboratoire(
            @RequestPart("laboratoireCompletDTO") LaboratoireCompletDTO laboratoireDTO,
            @RequestPart(value = "logoFile", required = false) MultipartFile logo
    ) throws IOException {
        Laboratoire createdLaboratoire = laboratoireService.createLaboratoireComplet(laboratoireDTO, logo);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLaboratoire);
    }

    @GetMapping
    public ResponseEntity<List<Laboratoire>> getAllLaboratoires() {
        List<Laboratoire> laboratoires = laboratoireService.getAllLaboratoires();
        return ResponseEntity.ok(laboratoires);
    }


    @GetMapping("/laboratoire/{id}")
    public ResponseEntity<LaboratoireCompletDTO> getLaboratoireById(@PathVariable Long id) {
        System.out.println("oui hia") ;
        LaboratoireCompletDTO laboratoire = laboratoireService.getLaboratoireInfos(id);
        System.out.println(laboratoire.getLaboratoire().getNrc());
        return ResponseEntity.ok(laboratoire);
    }

    @GetMapping("/laboratoireDTO/{id}")
    public ResponseEntity<LaboratoireDTO> getLaboratoireDTOById(@PathVariable Long id) {
        System.out.println("oui hia") ;
        LaboratoireDTO laboratoire = laboratoireService.getLaboratoireInfos(id).getLaboratoire();
        System.out.println(laboratoire.getNrc());
        return ResponseEntity.ok(laboratoire);
    }

    @GetMapping("/{id}/nom")
    public ResponseEntity<String> getLaboratoireNameById(@PathVariable Long id) {
        String nomLaboratoire = laboratoireService.getLaboratoireNameById(id);
        return ResponseEntity.ok(nomLaboratoire);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteLaboratoire(@PathVariable Long id) {
        try {
            laboratoireService.deleteLaboratoire(id);
            return ResponseEntity.ok("Laboratoire deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting the Laboratoire: " + e.getMessage());
        }
    }

    // Global Exception Handlers
    @ExceptionHandler({
            LaboratoireServiceImpl.ResourceNotFoundException.class,
            jakarta.validation.ValidationException.class
    })
    public ResponseEntity<String> handleExceptions(Exception ex) {
        if (ex instanceof LaboratoireServiceImpl.ResourceNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ex.getMessage());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Validation error: " + ex.getMessage());
        }
    }
}