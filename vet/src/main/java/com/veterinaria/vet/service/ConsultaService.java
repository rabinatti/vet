package com.veterinaria.vet.service;

import com.veterinaria.vet.repository.ClienteRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.veterinaria.vet.model.Consulta;
import com.veterinaria.vet.repository.ConsultaRepository;
import com.veterinaria.vet.repository.PetRepository;
import com.veterinaria.vet.repository.FuncionarioRepository;

@Service
public class ConsultaService {

    private final ClienteRepository clienteRepository;
    private final ConsultaRepository consultaRepository;
    private final PetRepository petRepository;
    private final FuncionarioRepository funcionarioRepository;

    public ConsultaService(ConsultaRepository consultaRepository, PetRepository petRepository, FuncionarioRepository funcionarioRepository, ClienteRepository clienteRepository) {
        this.consultaRepository = consultaRepository;
        this.petRepository = petRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.clienteRepository = clienteRepository;
    }

    public Optional<Consulta> salvarConsulta(Consulta consulta) {
        // Pet
        if (consulta.getPet() == null || !petRepository.existsById(consulta.getPet().getId())) {
            return Optional.empty();
        }

        // Funcionario
        if (consulta.getFuncionario() == null || !funcionarioRepository.existsById(consulta.getFuncionario().getId())) {
            return Optional.empty();
        } 
        return Optional.of(consultaRepository.save(consulta));
    }

    public List<Consulta> getAlCosultas() {
        return consultaRepository.findAll();
    }

    public Optional<Consulta> getConsulta(Integer id) {
        return consultaRepository.findById(id);
    }

    public Optional<Consulta> updateConsulta(Integer id, Consulta newConsultaData) {
        // Pet
        if (newConsultaData.getPet() == null || !petRepository.existsById(newConsultaData.getPet().getId())) {
            return Optional.empty();
        }

        // Funcionario
        if (newConsultaData.getFuncionario() == null || !funcionarioRepository.existsById(newConsultaData.getFuncionario().getId())) {
            return Optional.empty();
        }
        
        return consultaRepository.findById(id).map(consulta -> {
            consulta.setDataConsulta(newConsultaData.getDataConsulta());
            consulta.setHoraConsulta(newConsultaData.getHoraConsulta());
            consulta.setPet(newConsultaData.getPet());
            consulta.setFuncionario(newConsultaData.getFuncionario());
            return consultaRepository.save(consulta);
        });
    }

    public boolean deleteConsulta(Integer id) {
        if(consultaRepository.existsById(id)) {
            consultaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
