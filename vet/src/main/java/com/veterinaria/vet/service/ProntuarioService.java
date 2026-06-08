package com.veterinaria.vet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.veterinaria.vet.model.Prontuario;
import com.veterinaria.vet.repository.ProntuarioRepository;
import com.veterinaria.vet.repository.PetRepository;

@Service
public class ProntuarioService {

    private final ProntuarioRepository prontuarioRepository;
    private final PetRepository petRepository;

    public ProntuarioService(ProntuarioRepository prontuarioRepository, PetRepository petRepository) {
        this.prontuarioRepository = prontuarioRepository;
        this.petRepository = petRepository;
    }

    public Optional<Prontuario> salvarProntuario(Prontuario prontuario) {
        if (prontuario.getPet() == null || !petRepository.existsById(prontuario.getPet().getId())) {
            return Optional.empty();
        }
        return Optional.of(prontuarioRepository.save(prontuario));
    }

    public List<Prontuario> getAllProntuarios() {
        return prontuarioRepository.findAll();
    }

    public Optional<Prontuario> getProntuario(Integer id) {
        return prontuarioRepository.findById(id);
    }

    public Optional<Prontuario> updateProntuario(Integer id, Prontuario newProntuarioData) {
        if (newProntuarioData.getPet() == null || !petRepository.existsById(newProntuarioData.getPet().getId())) {
            return Optional.empty();
        }
        return prontuarioRepository.findById(id).map(prontuario -> {
            prontuario.setPet(newProntuarioData.getPet());
            prontuario.setPesoPet(newProntuarioData.getPesoPet());
            prontuario.setDescricao(newProntuarioData.getDescricao());
            prontuario.setEstadoPet(newProntuarioData.getEstadoPet());
            return prontuarioRepository.save(prontuario);
        });
    }

    public boolean deleteProntuario(Integer id) {
        if(prontuarioRepository.existsById(id)) {
            prontuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

}