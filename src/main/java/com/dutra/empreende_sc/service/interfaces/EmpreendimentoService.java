package com.dutra.empreende_sc.service.interfaces;

import com.dutra.empreende_sc.entities.Empreendimento;

import java.util.List;
import java.util.Optional;

public interface EmpreendimentoService {

    Empreendimento criar(Empreendimento empreendimento);

    List<Empreendimento> listarTodos();

    Optional<Empreendimento> buscarPorId(Long id);

    Empreendimento atualizar(Long id, Empreendimento dadosAtualizados);

    void deletar(Long id);
}