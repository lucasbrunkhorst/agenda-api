package com.lucasbrunkhorst.agendaapi.api.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequest {

    private String name;

    private String lastName;

    private String email;

    private String cpf;

}
