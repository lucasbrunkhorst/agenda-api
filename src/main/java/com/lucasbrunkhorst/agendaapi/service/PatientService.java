package com.lucasbrunkhorst.agendaapi.service;


import com.lucasbrunkhorst.agendaapi.domain.entities.Patient;
import com.lucasbrunkhorst.agendaapi.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository repository;

    public Patient save(Patient patient) {
        boolean existCpf = false;
        Optional<Patient> optPatient = repository.findByCpf(patient.getCpf());

        if (optPatient.isPresent()) {
            if (!optPatient.get().getId().equals(patient.getId())) {
                existCpf = true;
            }
        }
        if (existCpf) {
            throw new NoResultException(" Cpf already exists! ");
        }
        return repository.save(patient);

    }

    public List<Patient> list() {
        return repository.findAll();
    }

    public Patient update(Long id, Patient patient) {
        Optional<Patient> optPatient = this.read(id);

        if(optPatient.isEmpty()) {
            throw new NoResultException(" Patient not found ! ");
        }
        patient.setId(id);

        return save(patient);
    }

    public Optional<Patient> read(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
