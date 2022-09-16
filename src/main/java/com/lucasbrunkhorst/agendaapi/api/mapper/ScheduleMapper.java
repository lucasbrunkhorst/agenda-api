package com.lucasbrunkhorst.agendaapi.api.mapper;


import com.lucasbrunkhorst.agendaapi.api.request.ScheduleRequest;
import com.lucasbrunkhorst.agendaapi.api.response.ScheduleResponse;
import com.lucasbrunkhorst.agendaapi.domain.entities.Schedule;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ScheduleMapper {

    private final ModelMapper modelMapper;


    public Schedule toSchedule(ScheduleRequest request) {
        return modelMapper.map(request, Schedule.class);
    }

    public ScheduleResponse toScheduleResponse(Schedule schedule) {
        return modelMapper.map(schedule, ScheduleResponse.class);
    }

    public List<ScheduleResponse> toScheduleResponseList(List<Schedule> schedules) {
        return schedules.stream()
                .map(this::toScheduleResponse)
                .collect(Collectors.toList());
    }

}
