package com.lucasbrunkhorst.agendaapi.controller;

import com.lucasbrunkhorst.agendaapi.domain.entities.Patient;
import com.lucasbrunkhorst.agendaapi.repository.PatientRepository;
import com.lucasbrunkhorst.agendaapi.service.PatientService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PatientControllerTest {

    PatientRepository repository;
    PatientService service;

    @Test
    @DisplayName(" Must find all patients with success ")
    public void mustFindAllPatients() {
        List<Patient> patients = service.list();


    }
}
