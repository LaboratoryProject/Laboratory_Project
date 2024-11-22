package com.laboratoire.laboratoire_service.controller;

import com.laboratoire.laboratoire_service.dto.LaboratoireRequest;
import com.laboratoire.laboratoire_service.dto.LaboratoireResponse;
import com.laboratoire.laboratoire_service.service.LaboratoireService;
import com.laboratoire.analyse_service.notifications.EmailService;  // Import EmailService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/laboratoires")
public class LaboratoireController {

    private final LaboratoireService laboratoireService;
    private final EmailService emailService;  // Inject EmailService

    // Constructor injection for both services
    @Autowired
    public LaboratoireController(LaboratoireService laboratoireService, EmailService emailService) {
        this.laboratoireService = laboratoireService;
        this.emailService = emailService;  // Initialize EmailService
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createLaboratoire(@RequestBody LaboratoireRequest laboratoireRequest) {
        laboratoireService.createLaboratoire(laboratoireRequest);

        // Send email notification after laboratoire creation
        String recipientEmail = "afraeafrae01@gmail.com";
        String subject = "New Laboratoire Created";
        String body = "A new laboratoire has been created: " + laboratoireRequest.getNom();

        // Call the EmailService to send the email
        emailService.sendEmail(recipientEmail, subject, body);
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
