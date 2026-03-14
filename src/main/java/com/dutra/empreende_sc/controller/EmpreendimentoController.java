package com.dutra.empreende_sc.controller;

import com.dutra.empreende_sc.dtos.EmpreendimentoDtoInput;
import com.dutra.empreende_sc.dtos.EmpreendimentoDtoOutput;
import com.dutra.empreende_sc.dtos.EmpreendimentoUpdateInput;
import com.dutra.empreende_sc.entities.Empreendimento;
import com.dutra.empreende_sc.service.interfaces.EmpreendimentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<List<EmpreendimentoDtoInput>> listarTodos() {
        List<EmpreendimentoDtoInput> lista = service.listarTodos().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpreendimentoDtoInput> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(empreendimento -> ResponseEntity.ok(converterParaDTO(empreendimento)))
                .orElse(ResponseEntity.notFound().build());
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

    private Empreendimento converterParaEntidade(EmpreendimentoDtoInput dto) {
        Empreendimento entidade = new Empreendimento();

        entidade.setNomeEmpreendimento(dto.nomeEmpreendimento());
        entidade.setNomeEmpreendedor(dto.nomeEmpreendedor());
        entidade.setMunicipio(dto.municipio());
        entidade.setSegmento(dto.segmento());
        entidade.setContato(dto.contato());
        entidade.setStatus(dto.status());
        return entidade;
    }

    private EmpreendimentoDtoInput converterParaDTO(Empreendimento entidade) {
        return new EmpreendimentoDtoInput(
                entidade.id(),
                entidade.nomeEmpreendimento(),
                entidade.nomeEmpreendedor(),
                entidade.municipio(),
                entidade.segmento(),
                entidade.contato(),
                entidade.status()
        );
    }
}