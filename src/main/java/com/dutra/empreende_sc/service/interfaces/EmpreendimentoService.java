package com.dutra.empreende_sc.service.interfaces;

import com.dutra.empreende_sc.dtos.EmpreendimentoDtoInput;
import com.dutra.empreende_sc.dtos.EmpreendimentoDtoOutput;
import com.dutra.empreende_sc.dtos.EmpreendimentoUpdateInput;

import java.util.List;

public interface EmpreendimentoService {

    EmpreendimentoDtoOutput criar(EmpreendimentoDtoInput empreendimento);

    List<EmpreendimentoDtoOutput> listarTodos();

    EmpreendimentoDtoOutput buscarPorId(Long id);

    EmpreendimentoDtoOutput atualizar(Long id, EmpreendimentoUpdateInput empreendimentoAtualizado);

    void deletar(Long id);
}