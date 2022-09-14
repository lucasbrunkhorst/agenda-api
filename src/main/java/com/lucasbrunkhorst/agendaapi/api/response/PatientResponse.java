package com.lucasbrunkhorst.agendaapi.api.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientResponse {

    private Long id;

    private String name;

    private String lastName;

    private String email;

    private String cpf;

}
