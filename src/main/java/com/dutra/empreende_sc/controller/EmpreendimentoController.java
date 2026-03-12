package com.dutra.empreende_sc.controller;

import com.dutra.empreende_sc.entities.Empreendimento;
import com.dutra.empreende_sc.service.interfaces.EmpreendimentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empreendimentos")
public class EmpreendimentoController {

    private final EmpreendimentoService service;

    public EmpreendimentoController(EmpreendimentoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Empreendimento> criar(@RequestBody Empreendimento empreendimento) {
        Empreendimento novoEmpreendimento = service.criar(empreendimento);
        return new ResponseEntity<>(novoEmpreendimento, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Empreendimento>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empreendimento> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empreendimento> atualizar(@PathVariable Long id, @RequestBody Empreendimento empreendimento) {
        try {
            Empreendimento atualizado = service.atualizar(id, empreendimento);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}