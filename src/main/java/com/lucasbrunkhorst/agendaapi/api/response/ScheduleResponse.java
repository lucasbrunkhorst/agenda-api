package com.lucasbrunkhorst.agendaapi.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleResponse {

    private Long id;
    private String description;
    private LocalDateTime dateTimeHour;
    private PatientResponse patient;
}
