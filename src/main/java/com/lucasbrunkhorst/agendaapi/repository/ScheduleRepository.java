package com.lucasbrunkhorst.agendaapi.repository;

import com.lucasbrunkhorst.agendaapi.domain.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Optional<Schedule> findByDateHourTime (LocalDateTime dateHour);
}
