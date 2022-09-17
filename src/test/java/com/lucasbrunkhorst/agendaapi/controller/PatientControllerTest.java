package com.lucasbrunkhorst.agendaapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucasbrunkhorst.agendaapi.api.request.PatientRequest;
import com.lucasbrunkhorst.agendaapi.domain.entities.Patient;
import com.lucasbrunkhorst.agendaapi.exception.BusinessException;
import com.lucasbrunkhorst.agendaapi.repository.PatientRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class PatientControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PatientRepository repository;

    @BeforeEach
    void up() {
        Patient patient = new Patient();
        patient.setName("Joao");
        patient.setLastName("Carlos");
        patient.setCpf("123");
        patient.setEmail("joaozinho@hotmail.com");
        repository.save(patient);
    }

    @AfterEach
    void down() {
        repository.deleteAll();
    }

    @Test
    @DisplayName(" Must list all patients ")
    void listAllPatients() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/patient"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName(" Must save patients ")
    void savePatients() throws Exception {

        PatientRequest patient = PatientRequest.builder()
                .email("joao@hotmail.com")
                .name("Joao")
                .lastName("Carlos")
                .cpf("234")
                .build();

        String patientRequest = objectMapper.writeValueAsString(patient);

        mockMvc.perform(MockMvcRequestBuilders.post("/patient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(patientRequest)
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    @DisplayName(" Must have error when save patient with cpf existents ")
    void errorSavePatientsWithCpfExists() throws Exception {

        PatientRequest patient = PatientRequest.builder()
                .email("alexandre@hotmail.com")
                .name("Alexandre")
                .lastName("Medeiros")
                .cpf("123")
                .build();

        String patientRequest = objectMapper.writeValueAsString(patient);

        mockMvc.perform(MockMvcRequestBuilders.post("/patient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(patientRequest)
                )
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof BusinessException))
                .andDo(MockMvcResultHandlers.print());

    }
}
