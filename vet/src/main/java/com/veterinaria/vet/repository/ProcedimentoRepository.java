package com.veterinaria.vet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.veterinaria.vet.model.Procedimento;

@Repository
public interface ProcedimentoRepository extends JpaRepository<Procedimento, Integer>{
     boolean existsByNomeProcedimento(String nomeProcedimento);
}