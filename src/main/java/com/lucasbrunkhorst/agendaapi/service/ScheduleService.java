package com.lucasbrunkhorst.agendaapi.service;

import com.lucasbrunkhorst.agendaapi.domain.entities.Patient;
import com.lucasbrunkhorst.agendaapi.domain.entities.Schedule;
import com.lucasbrunkhorst.agendaapi.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository repository;
    private final PatientService patientService;

    public List<Schedule> listAllSchedule() {
        return repository.findAll();
    }

    public Optional<Schedule> searchSchedule(Long id) {
        return repository.findById(id);
    }

    public Schedule createSchedule(Schedule schedule) {
        Optional<Patient> optPatient = patientService.read(schedule.getPatient().getId());

        if (optPatient.isEmpty()) {
            throw new NoResultException(" Patient ID not found !");
        }

        Optional<Schedule> optByHour = repository.findByDateHourTime(schedule.getDateHourTime());

        if (optByHour.isPresent()) {
            throw new NoResultException(" Already exists scheduling for this date !");
        }

        schedule.setPatient(optPatient.get());
        schedule.setDateCreation(LocalDateTime.now());

        return repository.save(schedule);
    }

    public Optional<Schedule> updateSchedule(Long id) {
        return repository.findById(id);
    }

    public void deleteSchedule(Long id) {
        repository.deleteById(id);
    }

}
