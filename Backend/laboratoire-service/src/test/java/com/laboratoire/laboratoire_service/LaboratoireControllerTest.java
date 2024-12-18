package com.laboratoire.laboratoire_service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laboratoire.laboratoire_service.controller.LaboratoireController;
import com.laboratoire.laboratoire_service.dto.LaboratoireRequest;
import com.laboratoire.laboratoire_service.dto.LaboratoireResponse;
import com.laboratoire.laboratoire_service.service.LaboratoireServiceImpl;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LaboratoireController.class)
class LaboratoireControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LaboratoireServiceImpl laboratoireService;

    @Autowired
    private ObjectMapper objectMapper;

    private LaboratoireRequest laboratoireRequest;

    @BeforeEach
    void setUp() {
        LaboratoireRequest request = new LaboratoireRequest("Lab A", "logo.png", "NRC123",
                true, LocalDate.now());

    }

    @Test
    void createSimpleLaboratoireTest() throws Exception {
        LaboratoireResponse response = new LaboratoireResponse(
                "Laboratoire Test", // nom
                "logoTest.png",     // logo
                null,               // nrc (par exemple un tableau vide ou null si non nécessaire)
                "NRC123",           // laboratoireNrc
                true,               // active
                LocalDate.now()     // dateActivation (ou une autre date pertinente)
        );

        when(laboratoireService.createLaboratoire(any(LaboratoireRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/laboratoires")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(laboratoireRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nom").value("Laboratoire Test"));
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