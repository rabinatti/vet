package com.veterinaria.vet.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;

import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "funcionarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Funcionario extends Pessoa{
    @ToString.Include
    private double salario;
    
    @ToString.Include
    private LocalDate dataContratacao;

    @ToString.Include
    private boolean funcionarioAtivo;

    private String senhaFuncionario;
}
