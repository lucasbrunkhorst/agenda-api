package com.lucasbrunkhorst.agendaapi.api.controller;

import com.lucasbrunkhorst.agendaapi.api.mapper.PatientMapper;
import com.lucasbrunkhorst.agendaapi.api.request.PatientRequest;
import com.lucasbrunkhorst.agendaapi.api.response.PatientResponse;
import com.lucasbrunkhorst.agendaapi.domain.entities.Patient;
import com.lucasbrunkhorst.agendaapi.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService service;
    private final PatientMapper patientMapper;

    @PostMapping
    public ResponseEntity<PatientResponse> create(@Valid @RequestBody PatientRequest request) {
        Patient patient = patientMapper.toPatient(request);
        Patient savePatient = service.save(patient);
        PatientResponse patientResponse = patientMapper.toPatienceResponse(savePatient);

        return ResponseEntity.status(HttpStatus.CREATED).body(patientResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponse> read(@PathVariable Long id) {
        Optional<Patient> optPatient = service.read(id);

        if (optPatient.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(patientMapper.toPatienceResponse(optPatient.get()));
    }

    @GetMapping
    public ResponseEntity<List<PatientResponse>> list() {
        List<Patient> patient = service.list();
        List<PatientResponse> patientResponses = patientMapper.toPatienceResponseList(patient);

        return ResponseEntity.status(HttpStatus.OK).body(patientResponses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponse> update(@PathVariable Long id, @Valid @RequestBody PatientRequest request) {
        Patient patient = patientMapper.toPatient(request);
        Patient savePatient = service.update(id, patient);
        PatientResponse patientResponse = patientMapper.toPatienceResponse(savePatient);

        return ResponseEntity.status(HttpStatus.OK).body(patientResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

