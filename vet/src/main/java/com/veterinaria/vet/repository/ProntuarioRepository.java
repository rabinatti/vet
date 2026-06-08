package com.veterinaria.vet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.veterinaria.vet.model.Prontuario;

@Repository
public interface ProntuarioRepository extends JpaRepository<Prontuario, Integer> {
    
}
