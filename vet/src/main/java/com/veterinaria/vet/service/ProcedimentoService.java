package com.veterinaria.vet.service;

import com.veterinaria.vet.model.Procedimento;
import com.veterinaria.vet.repository.ProcedimentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProcedimentoService {

    private final ProcedimentoRepository procedimentoRepository;

    public ProcedimentoService(ProcedimentoRepository procedimentoRepository) {
        this.procedimentoRepository = procedimentoRepository;
    }

    public Optional<Procedimento> salvarProcedimento(Procedimento procedimento) {
        // Valida nome duplicado
        if (procedimento.getNomeProcedimento() == null ||
            procedimentoRepository.existsByNomeProcedimento(procedimento.getNomeProcedimento())) {
            return Optional.empty();
        }
        return Optional.of(procedimentoRepository.save(procedimento));
    }

    public List<Procedimento> getAllProcedimentos() {
        return procedimentoRepository.findAll();
    }

    public Optional<Procedimento> getProcedimento(Integer id) {
        return procedimentoRepository.findById(id);
    }

    public Optional<Procedimento> updateProcedimento(Integer id, Procedimento newData) {
        // Valida nome duplicado (ignora o próprio registro)
        if (newData.getNomeProcedimento() == null) {
            return Optional.empty();
        }

        return procedimentoRepository.findById(id).map(procedimento -> {
            procedimento.setNomeProcedimento(newData.getNomeProcedimento());
            procedimento.setPreco(newData.getPreco());
            return procedimentoRepository.save(procedimento);
        });
    }

    public boolean deleteProcedimento(Integer id) {
        if (procedimentoRepository.existsById(id)) {
            procedimentoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}