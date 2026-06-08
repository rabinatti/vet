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

import com.veterinaria.vet.model.Procedimento;
import com.veterinaria.vet.service.ProcedimentoService;

@RestController
@RequestMapping("/vet")
public class ProcedimentoController {

    private final ProcedimentoService procedimentoService;

    public ProcedimentoController(ProcedimentoService procedimentoService) {
        this.procedimentoService = procedimentoService;
    }

    @PostMapping("/procedimentos")
    @ResponseStatus(org.springframework.http.HttpStatus.CREATED)
    public ResponseEntity<Procedimento> postProcedimento(@RequestBody Procedimento procedimento) {
        return procedimentoService.salvarProcedimento(procedimento)
                .map(procedimentoCriado -> ResponseEntity.ok(procedimentoCriado))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/procedimentos")
    public ResponseEntity<List<Procedimento>> allProcedimentos() {
        List<Procedimento> procedimentos = procedimentoService.getAllProcedimentos();
        return ResponseEntity.ok(procedimentos);
    }

    @GetMapping("/procedimentos/{id}")
    public ResponseEntity<Procedimento> getProcedimentoById(@PathVariable Integer id) {
        return procedimentoService.getProcedimento(id)
                .map(procedimento -> ResponseEntity.ok(procedimento))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/procedimentos/{id}")
    public ResponseEntity<Procedimento> updateProcedimento(@PathVariable Integer id, @RequestBody Procedimento procedimento) {
        return procedimentoService.updateProcedimento(id, procedimento)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/procedimentos/{id}")
    public ResponseEntity<Void> deleteProcedimento(@PathVariable Integer id) {
        boolean deleted = procedimentoService.deleteProcedimento(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}