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

import com.veterinaria.vet.model.Consulta;
import com.veterinaria.vet.service.ConsultaService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/vet")
public class ConsultaController {
    
    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @PostMapping("/consultas")
    @ResponseStatus(org.springframework.http.HttpStatus.CREATED)
    public ResponseEntity<Consulta> postMethodConsulta(@RequestBody Consulta consulta) {
        return consultaService.salvarConsulta(consulta)
                .map(consultaCriada -> ResponseEntity.ok(consultaCriada))
               .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/consultas")
    public ResponseEntity<List<Consulta>> allConsultas() {
        List<Consulta> consultas = consultaService.getAlCosultas();
        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/consultas/{id}")
    public ResponseEntity<Consulta> getConsultaById(@PathVariable Integer id) {
        return consultaService.getConsulta(id)
                .map(consulta -> ResponseEntity.ok(consulta))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/consultas/{id}")
    public ResponseEntity<Consulta> updateConsulta(@PathVariable Integer id, @RequestBody Consulta consulta) {
        return consultaService.updateConsulta(id, consulta)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());        
    }

    @DeleteMapping("/consultas/{id}")
    public ResponseEntity<Void> deleteConsulta(@PathVariable Integer id) {
        boolean deleted = consultaService.deleteConsulta(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
