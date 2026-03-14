package com.dutra.empreende_sc.dtos;

import com.dutra.empreende_sc.entities.enums.SegmentoAtuacao;
import com.dutra.empreende_sc.entities.enums.StatusEmpreendimento;

public record EmpreendimentoDtoOutput(
        Long id,
        String nomeEmpreendimento,
        String nomeEmpreendedor,
        String municipio,
        SegmentoAtuacao segmento,
        String contato,
        StatusEmpreendimento status
) {
}