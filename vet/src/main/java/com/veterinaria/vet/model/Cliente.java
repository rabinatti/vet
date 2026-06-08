package com.veterinaria.vet.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;

import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Cliente extends Pessoa {
    @ToString.Include
    private boolean clienteAtivo;
}
