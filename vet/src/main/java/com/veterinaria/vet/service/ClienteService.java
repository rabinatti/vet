package com.veterinaria.vet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.veterinaria.vet.model.Cliente;
import com.veterinaria.vet.repository.ClienteRepository;

@Service
public class ClienteService {
    
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente salvarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> getCliente(Integer id) {
        return clienteRepository.findById(id);
    }

    public Optional<Cliente> updateCliente(Integer id, Cliente newClienteData) {
        return clienteRepository.findById(id).map(cliente -> {
            cliente.setNome(newClienteData.getNome());
            cliente.setCpf(newClienteData.getCpf());
            cliente.setDataNascimento(newClienteData.getDataNascimento());
            cliente.setClienteAtivo(newClienteData.isClienteAtivo());
            return clienteRepository.save(cliente);
        });
    }

    public boolean deleteCliente(Integer id) {
        if(clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
