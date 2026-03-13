package com.dutra.empreende_sc.service;

import com.dutra.empreende_sc.dtos.EmpreendimentoDtoInput;
import com.dutra.empreende_sc.dtos.EmpreendimentoDtoOutput;
import com.dutra.empreende_sc.entities.Empreendimento;
import com.dutra.empreende_sc.exceptions.EntidadeNaoEncontradaException;
import com.dutra.empreende_sc.repository.EmpreendimentoRepository;
import com.dutra.empreende_sc.service.interfaces.EmpreendimentoService;

import com.dutra.empreende_sc.service.utils.EmpreendimentoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmpreendimentoServiceImpl implements EmpreendimentoService {

    private final EmpreendimentoRepository repository;
    private final EmpreendimentoMapper mapper;

    public EmpreendimentoServiceImpl(EmpreendimentoRepository repository, EmpreendimentoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    @Override
    public EmpreendimentoDtoOutput criar(EmpreendimentoDtoInput empreendimento) {
        Empreendimento novoEmpreendimento = mapper.toEntity(empreendimento);

        return mapper.toOutput(repository.save(novoEmpreendimento));
    }

    @Transactional(readOnly = true)
    @Override
    public List<EmpreendimentoDtoOutput> listarTodos() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public EmpreendimentoDtoOutput buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Empreendimento não encontrado com o id: " + id)
        );
    }

    @Override
    public EmpreendimentoDtoOutput atualizar(Long id, EmpreendimentoDtoInput empreendimentoAtualizado) {

        return repository.findById(id).map(empreendimentoExistente -> {
            empreendimentoExistente.setNomeEmpreendimento(empreendimentoAtualizado.nomeEmpreendimento());
            empreendimentoExistente.setNomeEmpreendedor(empreendimentoAtualizado.nomeEmpreendedor());
            empreendimentoExistente.setMunicipio(empreendimentoAtualizado.municipio());
            empreendimentoExistente.setSegmento(empreendimentoAtualizado.segmento());
            empreendimentoExistente.setContato(empreendimentoAtualizado.contato());
            empreendimentoExistente.setStatus(empreendimentoAtualizado.status());
            return repository.save(empreendimentoExistente);
        })
                .orElseThrow(() -> new RuntimeException("Empreendimento não encontrado com o id: " + id));
    }

    @Transactional
    @Override
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
