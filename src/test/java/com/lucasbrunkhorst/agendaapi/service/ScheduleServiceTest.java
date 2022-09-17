package com.lucasbrunkhorst.agendaapi.service;

import com.lucasbrunkhorst.agendaapi.domain.entities.Patient;
import com.lucasbrunkhorst.agendaapi.domain.entities.Schedule;
import com.lucasbrunkhorst.agendaapi.repository.ScheduleRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.NoResultException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ScheduleServiceTest {

    @InjectMocks
    ScheduleService service;

    @Mock
    PatientService patientService;

    @Mock
    ScheduleRepository repository;

    @Captor
    ArgumentCaptor<Schedule> scheduleArgumentCaptor;

    @Test
    @DisplayName(" Must save scheduling with success ")
    void createSchedule() {

        LocalDateTime now = LocalDateTime.now();
        Schedule schedule = new Schedule();
        schedule.setDescription("Description of scheduling ");
        schedule.setDateHourTime(now);

        Patient patient = new Patient();
        patient.setId(1L);
        patient.setName("Patient");

        schedule.setPatient(patient);

        Mockito.when(patientService.read(schedule.getPatient().getId())).thenReturn(Optional.of(patient));
        Mockito.when(repository.findByDateHourTime(now)).thenReturn(Optional.empty());

        service.createSchedule(schedule);

        Mockito.verify(patientService).read(schedule.getPatient().getId());
        Mockito.verify(repository).findByDateHourTime(now);

        Mockito.verify(repository).save(scheduleArgumentCaptor.capture());
        Schedule saveSchedule = scheduleArgumentCaptor.getValue();

        Assertions.assertThat(saveSchedule.getDateCreation()).isNotNull();
        Assertions.assertThat(saveSchedule.getPatient()).isNotNull();
    }

    @Test
    @DisplayName(" Must not save scheduling without patient ")
    void createScheduleErrorPatientNotFound() {

        LocalDateTime now = LocalDateTime.now();
        Schedule schedule = new Schedule();
        schedule.setDescription("A new scheduling ");
        schedule.setDateHourTime(now);

        Patient patient = new Patient();
        patient.setId(1L);
        patient.setName("Patient");

        schedule.setPatient(patient);

        Mockito.when(patientService.read(ArgumentMatchers.any())).thenReturn(Optional.empty());

        NoResultException noResultException = assertThrows(NoResultException.class, () -> {
            service.createSchedule(schedule);
        });

        Assertions.assertThat(noResultException.getMessage()).isEqualTo(" Patient ID not found !");
    }

    @Test
    @DisplayName("Must find all scheduling ")
    void mustFindAllScheduling() {

        LocalDateTime now = LocalDateTime.now();
        Schedule schedule = new Schedule();
        schedule.setDescription("New scheduling ");
        schedule.setDateCreation(now);
        service.listAllSchedule();

        Mockito.when(service.listAllSchedule()).thenReturn(List.of(schedule));

        Assertions.assertThat(service.listAllSchedule()).hasSize(1);
    }

}