package com.veterinaria.vet.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.veterinaria.vet.model.Prontuario;
import com.veterinaria.vet.service.ProntuarioService;
import com.veterinaria.vet.model.Pet;


@RestController
@RequestMapping("/vet")
public class ProntuarioController {

    private final ProntuarioService prontuarioService;

    public ProntuarioController(ProntuarioService prontuarioService) {
        this.prontuarioService = prontuarioService;
    }

    // Rota vem do id do pet
    @PostMapping("/pets/{idPet}/prontuario")
    public ResponseEntity<Prontuario> postMethodProntuario(@PathVariable Integer idPet, @RequestBody Prontuario prontuario) {
        Pet pet = new Pet();
        pet.setId(idPet);
        prontuario.setPet(pet);

        return prontuarioService.salvarProntuario(prontuario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/pets/{idPet}/prontuario/{idProntuario}")
    public ResponseEntity<Prontuario> getProntuarioById(@PathVariable Integer idPet, @PathVariable Integer idProntuario) {
        return prontuarioService.getProntuario(idProntuario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/pets/{idPet}/prontuario/{idProntuario}")
    public ResponseEntity<Prontuario> updateProntuario(
            @PathVariable Integer idPet, 
            @PathVariable Integer idProntuario, 
            @RequestBody Prontuario prontuario) {
        
        Pet pet = new Pet();
        pet.setId(idPet);
        prontuario.setPet(pet);

        return prontuarioService.updateProntuario(idProntuario, prontuario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/pets/{idPet}/prontuario/{idProntuario}")
    public ResponseEntity<Void> deleteProntuario(@PathVariable Integer idPet, @PathVariable Integer idProntuario) {
        boolean deleted = prontuarioService.deleteProntuario(idProntuario);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
