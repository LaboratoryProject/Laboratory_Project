package com.laboratoire.laboratoire_service.controller;

import com.laboratoire.laboratoire_service.dto.LaboratoireRequest;
import com.laboratoire.laboratoire_service.dto.LaboratoireResponse;
import com.laboratoire.laboratoire_service.dto.LaboratoireCompletDTO;
import com.laboratoire.laboratoire_service.model.Laboratoire;
import com.laboratoire.laboratoire_service.service.LaboratoireServiceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/laboratoires")
public class LaboratoireController {

    private final LaboratoireServiceImpl laboratoireService;
    //  private final EmailService emailService;  // Inject EmailService
    // Injection de dépendance via constructeur
    public LaboratoireController(LaboratoireServiceImpl laboratoireService) {
        this.laboratoireService = laboratoireService;
        // this.emailService = emailService;  // Initialize EmailService
    }

    // Endpoint pour créer un laboratoire simple
    @PostMapping
    public ResponseEntity<LaboratoireResponse> creerLaboratoire(
            @Valid @RequestBody LaboratoireRequest laboratoireRequest) {
        LaboratoireResponse response = laboratoireService.createLaboratoire(laboratoireRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /*   @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createLaboratoire(@RequestBody LaboratoireRequest laboratoireRequest) {
        laboratoireService.createLaboratoire(laboratoireRequest);

        // Send email notification after laboratoire creation
        String recipientEmail = "afraeafrae01@gmail.com";
        String subject = "New Laboratoire Created";
        String body = "A new laboratoire has been created: " + laboratoireRequest.getNom();

        // Call the EmailService to send the email
        emailService.sendEmail(recipientEmail, subject, body);
    }*/

    // Endpoint pour créer un laboratoire complet (avec adresse et contact)
    @PostMapping("/complet")
    public ResponseEntity<Laboratoire> creerLaboratoireComplet(
            @Valid @RequestBody LaboratoireCompletDTO laboratoireCompletDTO) throws IOException {
        Laboratoire response = laboratoireService.createLaboratoireComplet(laboratoireCompletDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Endpoint pour récupérer tous les laboratoires
    @GetMapping
    public ResponseEntity<List<LaboratoireResponse>> getAllLaboratoires() {
        List<LaboratoireResponse> laboratoires = laboratoireService.getAllLaboratoires();
        return ResponseEntity.ok(laboratoires);
    }

    // Endpoint pour récupérer un laboratoire par ID
    @GetMapping("/{id}")
    public ResponseEntity<LaboratoireResponse> getLaboratoireById(@PathVariable Long id) {
        LaboratoireResponse laboratoire = laboratoireService.getLaboratoireById(id);
        return ResponseEntity.ok(laboratoire);
    }

    // Endpoint pour récupérer le nom d'un laboratoire par ID
    @GetMapping("/{id}/nom")
    public ResponseEntity<String> getLaboratoireNameById(@PathVariable Long id) {
        String nomLaboratoire = laboratoireService.getLaboratoireNameById(id);
        return ResponseEntity.ok(nomLaboratoire);
    }



    // Gestion des exceptions spécifiques au contrôleur
    @ExceptionHandler(LaboratoireServiceImpl.ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(LaboratoireServiceImpl.ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Gestion des exceptions de validation
    @ExceptionHandler(jakarta.validation.ValidationException.class)
    public ResponseEntity<String> handleValidationException(jakarta.validation.ValidationException ex) {
        return new ResponseEntity<>("Erreur de validation : " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}