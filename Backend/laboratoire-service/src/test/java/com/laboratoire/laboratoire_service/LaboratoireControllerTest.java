package com.laboratoire.laboratoire_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laboratoire.laboratoire_service.controller.LaboratoireController;
import com.laboratoire.laboratoire_service.dto.AdresseDTO;
import com.laboratoire.laboratoire_service.dto.ContactLaboratoireDTO;
import com.laboratoire.laboratoire_service.dto.LaboratoireCompletDTO;
import com.laboratoire.laboratoire_service.dto.LaboratoireDTO;
import com.laboratoire.laboratoire_service.model.Laboratoire;
import com.laboratoire.laboratoire_service.service.LaboratoireServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest // Full context, including security and authentication
@AutoConfigureMockMvc // Configures MockMvc
class LaboratoireControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LaboratoireServiceImpl laboratoireService;

    @Test
    void createCompletLaboratoireTest() throws Exception {
        // Prepare the nested DTOs
        LaboratoireDTO laboratoireDTO = new LaboratoireDTO();
        laboratoireDTO.setNom("Lab B");
        laboratoireDTO.setNrc("NRC456");
        laboratoireDTO.setLogo("logo.png");
        laboratoireDTO.setActive(true);
        laboratoireDTO.setDateActivation(LocalDate.now());

        AdresseDTO adresseDTO = new AdresseDTO();
        adresseDTO.setNumVoie("123");
        adresseDTO.setNomVoie("Main Street");
        adresseDTO.setVille("Paris");
        adresseDTO.setCommune("Paris");
        adresseDTO.setCodePostal("75001");

        ContactLaboratoireDTO contactDTO = new ContactLaboratoireDTO();
        contactDTO.setEmail("labB@example.com");
        contactDTO.setNumTel("0123456789");
        contactDTO.setFax("0123456789");

        // Combine into LaboratoireCompletDTO
        LaboratoireCompletDTO completDTO = new LaboratoireCompletDTO(laboratoireDTO, adresseDTO, contactDTO);

        // Simulate a multipart file for the logo
        MockMultipartFile logoFile = new MockMultipartFile(
                "logoFile", "logo.png", MediaType.IMAGE_PNG_VALUE, "Fake Image Content".getBytes()
        );

        // Simulate a multipart file for LaboratoireCompletDTO
        MockMultipartFile dtoFile = new MockMultipartFile(
                "laboratoireCompletDTO", "", MediaType.APPLICATION_JSON_VALUE,
                objectMapper.writeValueAsBytes(completDTO)
        );

        // Simulated response from the service
        Laboratoire createdLaboratoire = new Laboratoire(1L, "Lab B", "logo.png", "NRC456", true, LocalDate.now());
        when(laboratoireService.createLaboratoireComplet(any(LaboratoireCompletDTO.class), any()))
                .thenReturn(createdLaboratoire);

        // Perform the multipart request
        mockMvc.perform(multipart("/api/laboratoires/complet")
                        .file(dtoFile)
                        .file(logoFile)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nom").value("Lab B"))
                .andExpect(jsonPath("$.logo").value("logo.png"))
                .andExpect(jsonPath("$.nrc").value("NRC456"))
                .andExpect(jsonPath("$.active").value(true));
    }

    @Test
    void getLaboratoireByIdTest() throws Exception {
        Long id = 1L;

        when(laboratoireService.getLaboratoireNameById(id)).thenReturn("Laboratoire Test");

        mockMvc.perform(get("/api/laboratoires/{id}/nom", id))
                .andExpect(status().isOk())
                .andExpect(content().string("Laboratoire Test"));
    }

    @Test
    void deleteLaboratoireTest() throws Exception {
        Mockito.doNothing().when(laboratoireService).deleteLaboratoire(1L);

        mockMvc.perform(delete("/api/laboratoires/delete/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().string("Laboratoire deleted successfully."));
    }
}
