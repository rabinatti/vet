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

import com.veterinaria.vet.model.Pet;
import com.veterinaria.vet.service.PetService;

@RestController
@RequestMapping("/vet")
public class PetController {
    
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping("/pets")
    @ResponseStatus(org.springframework.http.HttpStatus.CREATED)
    public ResponseEntity<Pet> postMethodName(@RequestBody Pet pet) {
        return petService.salvarPet(pet)
                .map(petCriado -> ResponseEntity.ok(petCriado))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/pets")
    public ResponseEntity<List<Pet>> allPets() {
        List<Pet> pets = petService.getAllPets();
        return ResponseEntity.ok(pets);
    }

    @GetMapping("/pets/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable Integer id) {
        return petService.getPet(id)
                .map(pet -> ResponseEntity.ok(pet))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/pets/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable Integer id, @RequestBody Pet pet) {
        return petService.updatePet(id, pet)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());    
    }

    @DeleteMapping("/pets/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Integer id) {
        boolean deleted = petService.deletePet(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
        
    }
}
