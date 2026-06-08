package com.veterinaria.vet.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "consultas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @ToString.Include
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(nullable = false)
    @ToString.Include
    private LocalDate dataConsulta;

    @Column(nullable = false)
    @ToString.Include
    private LocalTime horaConsulta;

    @ManyToOne 
    @JoinColumn(name = "id_pet", nullable = false)
    @ToString.Include
    private Pet pet;

    @ManyToOne 
    @JoinColumn(name = "id_funcionario", nullable = false)
    @ToString.Include
    private Funcionario funcionario;
}
