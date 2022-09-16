package com.lucasbrunkhorst.agendaapi.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = " Name is a required field ")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Last name is a required field ")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = " Cpf is a required field ")
    @Column(name = "cpf")
    private String cpf;

    @Column(name = "email")
    @Email
    private String email;

}


