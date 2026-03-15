package com.dutra.empreende_sc.service;

import com.dutra.empreende_sc.dtos.EmpreendimentoDtoInput;
import com.dutra.empreende_sc.dtos.EmpreendimentoDtoOutput;
import com.dutra.empreende_sc.dtos.EmpreendimentoUpdateInput;
import com.dutra.empreende_sc.entities.Empreendimento;
import com.dutra.empreende_sc.exceptions.EntidadeNaoEncontradaException;
import com.dutra.empreende_sc.exceptions.ViolacaoIntegridadeReferencialException;
import com.dutra.empreende_sc.repository.EmpreendimentoRepository;
import com.dutra.empreende_sc.service.interfaces.EmpreendimentoService;

import com.dutra.empreende_sc.service.utils.EmpreendimentoMapper;
import org.springframework.dao.DataIntegrityViolationException;
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
    public EmpreendimentoDtoOutput criar(EmpreendimentoDtoInput empreendimentoInput) {
        Empreendimento novoEmpreendimento = mapper.montarEntidade(empreendimentoInput);
        return mapper.toOutput(repository.save(novoEmpreendimento));
    }

    @Transactional(readOnly = true)
    @Override
    public List<EmpreendimentoDtoOutput> listarTodos() {
        return repository.findAll().stream().map(mapper::toOutput).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public EmpreendimentoDtoOutput buscarPorId(Long id) {
        return mapper.toOutput(repository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Empreendimento não encontrado para o id: " + id)
        ));
    }

    @Transactional
    @Override
    public EmpreendimentoDtoOutput atualizar(Long id, EmpreendimentoUpdateInput empreendimentoAtualizado) {

        Empreendimento entidade = repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Empreendimento não encontrado com o id: " + id));

        atualizaCamposDoEmpreendimento(empreendimentoAtualizado, entidade);

        Empreendimento entidadeSalva = repository.save(entidade);

        return mapper.toOutput(entidadeSalva);
    }

    @Transactional
    @Override
    public void deletar(Long id) {

        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoIntegridadeReferencialException("Exclusão de entidade viola a integridade referencial do banco de dados, para ID: ." + id);
        }
    }

    private void atualizaCamposDoEmpreendimento(EmpreendimentoUpdateInput dto, Empreendimento entidade) {
        if (dto == null || entidade == null) {
            return;
        }

        if (dto.nomeEmpreendimento() != null) {
            entidade.setNomeEmpreendimento(dto.nomeEmpreendimento());
        }
        if (dto.nomeEmpreendedor() != null) {
            entidade.setNomeEmpreendedor(dto.nomeEmpreendedor());
        }
        if (dto.municipio() != null) {
            entidade.setMunicipio(dto.municipio());
        }
        if (dto.segmento() != null) {
            entidade.setSegmento(dto.segmento());
        }
        if (dto.contato() != null) {
            entidade.setContato(dto.contato());
        }
        if (dto.status() != null) {
            entidade.setStatus(dto.status());
        }
    }
}
