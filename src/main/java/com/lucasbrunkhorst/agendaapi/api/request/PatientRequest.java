package com.lucasbrunkhorst.agendaapi.api.request;


import lombok.*;

@Builder
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
