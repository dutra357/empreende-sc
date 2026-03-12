package com.dutra.empreende_sc.service;

import com.dutra.empreende_sc.entities.Empreendimento;
import com.dutra.empreende_sc.repository.EmpreendimentoRepository;
import com.dutra.empreende_sc.service.interfaces.EmpreendimentoService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmpreendimentoServiceImpl implements EmpreendimentoService {

    private final EmpreendimentoRepository repository;

    public EmpreendimentoServiceImpl(EmpreendimentoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public Empreendimento criar(Empreendimento empreendimento) {
        return repository.save(empreendimento);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Empreendimento> listarTodos() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Empreendimento> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Empreendimento atualizar(Long id, Empreendimento dadosAtualizados) {

        return repository.findById(id).map(empreendimentoExistente -> {
            empreendimentoExistente.setNomeEmpreendimento(dadosAtualizados.nomeEmpreendimento());
            empreendimentoExistente.setNomeEmpreendedor(dadosAtualizados.nomeEmpreendedor());
            empreendimentoExistente.setMunicipio(dadosAtualizados.municipio());
            empreendimentoExistente.setSegmento(dadosAtualizados.segmento());
            empreendimentoExistente.setContato(dadosAtualizados.contato());
            empreendimentoExistente.setStatus(dadosAtualizados.status());
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
