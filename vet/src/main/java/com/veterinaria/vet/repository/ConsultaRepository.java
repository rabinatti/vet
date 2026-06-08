package com.veterinaria.vet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.veterinaria.vet.model.Consulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Integer> {
    
}
