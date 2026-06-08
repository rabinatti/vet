package com.veterinaria.vet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.veterinaria.vet.model.Funcionario;
import com.veterinaria.vet.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public Funcionario salvarFuncionario(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public List<Funcionario> getAllFuncinarios() {
        return funcionarioRepository.findAll();
    }

    public Optional<Funcionario> getFuncionario(Integer id) {
        return funcionarioRepository.findById(id);
    }

    public Optional<Funcionario> updateFuncionario(Integer id, Funcionario newFuncionarioData) {
        return funcionarioRepository.findById(id).map(funcionario -> {
            funcionario.setNome(newFuncionarioData.getNome());
            funcionario.setCpf(newFuncionarioData.getCpf());
            funcionario.setDataNascimento(newFuncionarioData.getDataNascimento());
            funcionario.setSalario(newFuncionarioData.getSalario());
            funcionario.setDataContratacao(newFuncionarioData.getDataContratacao());
            funcionario.setFuncionarioAtivo(newFuncionarioData.isFuncionarioAtivo());
            funcionario.setSenhaFuncionario(newFuncionarioData.getSenhaFuncionario());
            return funcionarioRepository.save(funcionario);
        });
    }

    public boolean deleteFuncionario(Integer id) {
        if(funcionarioRepository.existsById(id)) {
            funcionarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
