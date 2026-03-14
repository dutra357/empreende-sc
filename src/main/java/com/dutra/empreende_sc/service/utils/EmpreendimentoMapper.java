package com.dutra.empreende_sc.service.utils;

import com.dutra.empreende_sc.dtos.EmpreendimentoDtoInput;
import com.dutra.empreende_sc.dtos.EmpreendimentoDtoOutput;
import com.dutra.empreende_sc.entities.Empreendimento;
import org.springframework.stereotype.Component;

@Component
public class EmpreendimentoMapper {

    public EmpreendimentoDtoOutput toOutput(Empreendimento entidade) {

        if (entidade == null) {
            return null;
        }

        return new EmpreendimentoDtoOutput(
                entidade.id(),
                entidade.nomeEmpreendimento(),
                entidade.nomeEmpreendedor(),
                entidade.municipio(),
                entidade.segmento(),
                entidade.contato(),
                entidade.status()
        );
    }

    public Empreendimento toEntity(EmpreendimentoDtoInput dto) {

        if (dto == null) {
            return null;
        }

        Empreendimento entidade = new Empreendimento();
        entidade.setNomeEmpreendimento(dto.nomeEmpreendimento());
        entidade.setNomeEmpreendedor(dto.nomeEmpreendedor());
        entidade.setMunicipio(dto.municipio());
        entidade.setSegmento(dto.segmento());
        entidade.setContato(dto.contato());
        entidade.setStatus(dto.status());
        return entidade;
    }
}