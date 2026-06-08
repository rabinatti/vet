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

import com.veterinaria.vet.model.Funcionario;
import com.veterinaria.vet.service.FuncionarioService;

@RestController
@RequestMapping("/vet")
public class FuncionarioController {
    
    private final FuncionarioService funcionarioService;
    
    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping("/funcionarios")
    @ResponseStatus(org.springframework.http.HttpStatus.CREATED)
    public ResponseEntity<Funcionario> postMethodName(@RequestBody Funcionario funcionario) {
        Funcionario funcionarioCriado = funcionarioService.salvarFuncionario(funcionario);
        return ResponseEntity.ok(funcionarioCriado);
    }

    @GetMapping("/funcionarios")
    public ResponseEntity<List<Funcionario>> allFuncionarios() {
        List<Funcionario> funcionarios = funcionarioService.getAllFuncinarios();
        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping("/funcionarios/{id}")
    public ResponseEntity<Funcionario> getFuncionarioById(@PathVariable Integer id) {
        return funcionarioService.getFuncionario(id)
                .map(funcionario -> ResponseEntity.ok(funcionario))
                .orElse(ResponseEntity.notFound().build());
    }
    

    @PutMapping("/funcionarios/{id}")
    public ResponseEntity<Funcionario> updateFuncionario(@PathVariable Integer id, @RequestBody Funcionario funcionario) {
        return funcionarioService.updateFuncionario(id, funcionario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());        
    }

    @DeleteMapping("/funcionarios/{id}")
    public ResponseEntity<Void> deleteFuncionario(@PathVariable Integer id) {
        boolean deleted = funcionarioService.deleteFuncionario(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }    

}
