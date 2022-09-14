package com.lucasbrunkhorst.agendaapi.api.mapper;

import com.lucasbrunkhorst.agendaapi.api.request.PatientRequest;
import com.lucasbrunkhorst.agendaapi.api.response.PatientResponse;
import com.lucasbrunkhorst.agendaapi.domain.entities.Patient;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PatientMapper {

    private final ModelMapper modelMapper;

    public Patient toPatient(PatientRequest request) {
        return modelMapper.map(request, Patient.class);
    }

    public PatientResponse toPatienceResponse(Patient patient) {
        return modelMapper.map(patient, PatientResponse.class);
    }

    public List<PatientResponse> toPatienceResponseList(List<Patient> patients) {
        return patients.stream()
                .map(this::toPatienceResponse)
                .collect(Collectors.toList());
    }

}
