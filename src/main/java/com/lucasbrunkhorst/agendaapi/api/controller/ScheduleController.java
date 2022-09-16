package com.lucasbrunkhorst.agendaapi.api.controller;

import com.lucasbrunkhorst.agendaapi.api.mapper.ScheduleMapper;
import com.lucasbrunkhorst.agendaapi.api.request.ScheduleRequest;
import com.lucasbrunkhorst.agendaapi.api.response.ScheduleResponse;
import com.lucasbrunkhorst.agendaapi.domain.entities.Schedule;
import com.lucasbrunkhorst.agendaapi.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService service;
    private final ScheduleMapper mapper;

    @GetMapping
    public ResponseEntity<List<ScheduleResponse>> searchAll() {
        List<Schedule> schedules = service.listAllSchedule();
        List<ScheduleResponse> scheduleResponse = mapper.toScheduleResponseList(schedules);

        return ResponseEntity.status(HttpStatus.OK).body(scheduleResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponse> searchOne(@PathVariable Long id) {
        Optional<Schedule> optSchedule = service.searchSchedule(id);

        if (optSchedule.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ScheduleResponse scheduleResponse = mapper.toScheduleResponse(optSchedule.get());

        return ResponseEntity.status(HttpStatus.OK).body(scheduleResponse);
    }

    @PostMapping
    public ResponseEntity<ScheduleResponse> saveSchedule(@Valid @RequestBody ScheduleRequest request) {
        Schedule schedule = mapper.toSchedule(request);
        Schedule saveSchedule = service.createSchedule(schedule);
        ScheduleResponse scheduleResponse = mapper.toScheduleResponse(saveSchedule);

        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleResponse);
    }

    @DeleteMapping("/{id}")
    public void deleteSchedule(@PathVariable Long id) {
        service.deleteSchedule(id);
    }
}
