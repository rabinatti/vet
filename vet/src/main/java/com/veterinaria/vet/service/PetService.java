package com.veterinaria.vet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.veterinaria.vet.model.Pet;
import com.veterinaria.vet.repository.ClienteRepository;
import com.veterinaria.vet.repository.PetRepository;

@Service
public class PetService {
    
    private final PetRepository petRepository;
    private final ClienteRepository clienteRepository;

    public PetService(PetRepository petRepository, ClienteRepository clienteRepository) {
        this.petRepository = petRepository;
        this.clienteRepository = clienteRepository;
    }

    public Optional<Pet> salvarPet(Pet pet) {
        // Verificação de o cliente existe
            // Verifica se o cliente é null || se o cliente existe no repositório verificando o id
        if (pet.getCliente() == null || !clienteRepository.existsById(pet.getCliente().getId())) {
            return Optional.empty();
        }
        return Optional.of(petRepository.save(pet));
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public Optional<Pet> getPet(Integer id) {
        return petRepository.findById(id);
    }

    public Optional<Pet> updatePet(Integer id, Pet newPetData) {
        // Mesma verificação que a anterior, verificando se o cliente existe
        if (newPetData.getCliente() == null || !clienteRepository.existsById(newPetData.getCliente().getId())) {
            return Optional.empty();
        }
        
        // Se o cliente existe, ele não entra no if e atualiza o pet
        return petRepository.findById(id).map(pet -> {
            pet.setNomePet(newPetData.getNomePet());
            pet.setRaca(newPetData.getRaca());
            pet.setDataNascimentoPet(newPetData.getDataNascimentoPet());
            pet.setCliente(newPetData.getCliente());
            pet.setPetAtivo(newPetData.isPetAtivo());
            pet.setDataFalecimento(newPetData.getDataFalecimento());
            return petRepository.save(pet);
        });
    }

    public boolean deletePet(Integer id) {
        if(petRepository.existsById(id)) {
            petRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
