package com.dutra.empreende_sc.controller;

import com.dutra.empreende_sc.dtos.EmpreendimentoDTO;
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
    public ResponseEntity<EmpreendimentoDTO> criar(@Valid @RequestBody EmpreendimentoDTO dto) {
        Empreendimento empreendimento = converterParaEntidade(dto);
        Empreendimento novoEmpreendimento = service.criar(empreendimento);

        return new ResponseEntity<>(converterParaDTO(novoEmpreendimento), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmpreendimentoDTO>> listarTodos() {
        List<EmpreendimentoDTO> lista = service.listarTodos().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpreendimentoDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(empreendimento -> ResponseEntity.ok(converterParaDTO(empreendimento)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpreendimentoDTO> atualizar(@PathVariable Long id, @Valid @RequestBody EmpreendimentoDTO dto) {
        try {
            Empreendimento dadosAtualizados = converterParaEntidade(dto);
            Empreendimento atualizado = service.atualizar(id, dadosAtualizados);
            return ResponseEntity.ok(converterParaDTO(atualizado));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    private Empreendimento converterParaEntidade(EmpreendimentoDTO dto) {
        Empreendimento entidade = new Empreendimento();

        entidade.setNomeEmpreendimento(dto.nomeEmpreendimento());
        entidade.setNomeEmpreendedor(dto.nomeEmpreendedor());
        entidade.setMunicipio(dto.municipio());
        entidade.setSegmento(dto.segmento());
        entidade.setContato(dto.contato());
        entidade.setStatus(dto.status());
        return entidade;
    }

    private EmpreendimentoDTO converterParaDTO(Empreendimento entidade) {
        return new EmpreendimentoDTO(
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