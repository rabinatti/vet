package com.veterinaria.vet.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "prontuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Prontuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @ToString.Include
    @EqualsAndHashCode.Include
    private Integer idProntuario;

    @OneToOne
    @JoinColumn(name = "pet_id", nullable = false, unique = true)
    @ToString.Include
    private Pet pet;

    @Column(name = "peso_pet", nullable = false)
    @ToString.Include
    private double pesoPet;

    @Column(nullable = false, length = 500)
    @ToString.Include
    private String descricao;

    @Column(name = "estado_pet", nullable = false)
    @ToString.Include
    private String estadoPet;
}
