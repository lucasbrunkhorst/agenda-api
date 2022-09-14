package com.lucasbrunkhorst.agendaapi.domain.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "date_hour")
    private LocalDateTime dateHour;

    @Column(name = "date_creation")
    private LocalDateTime dateCreation;

    @ManyToOne
    private Patient patient;


}
