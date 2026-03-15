package com.dutra.empreende_sc.controller;

import com.dutra.empreende_sc.dtos.EmpreendimentoDtoInput;
import com.dutra.empreende_sc.dtos.EmpreendimentoDtoOutput;
import com.dutra.empreende_sc.dtos.EmpreendimentoUpdateInput;
import com.dutra.empreende_sc.service.interfaces.EmpreendimentoService;
import jakarta.validation.Valid;
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
    public ResponseEntity<EmpreendimentoDtoOutput> criar(@Valid @RequestBody EmpreendimentoDtoInput novoEmpreendimento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(novoEmpreendimento));
    }

    @GetMapping
    public ResponseEntity<List<EmpreendimentoDtoOutput>> listarTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpreendimentoDtoOutput> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpreendimentoDtoOutput> atualizar(@PathVariable Long id, @Valid @RequestBody EmpreendimentoUpdateInput dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.atualizar(id, dto));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}