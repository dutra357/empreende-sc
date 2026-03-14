package com.dutra.empreende_sc.dtos;

import com.dutra.empreende_sc.entities.enums.SegmentoAtuacao;
import com.dutra.empreende_sc.entities.enums.StatusEmpreendimento;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record EmpreendimentoUpdateInput(

        @Size(min = 3, max = 100, message = "Se informado, o nome deve ter entre 3 e 100 caracteres.")
        String nomeEmpreendimento,

        @Size(min = 3, message = "Se informado, o nome do(a) empreendedor(a) não pode ser vazio ou muito curto.")
        String nomeEmpreendedor,

        @Size(min = 2, message = "Se informado, o município não pode ser vazio.")
        String municipio,

        SegmentoAtuacao segmento,

        @Size(min = 5, message = "Se informado, o e-mail não pode ser vazio ou de espaços em branco.")
        @Email(message = "O e-mail fornecido não possui um formato válido.")
        String contato,

        StatusEmpreendimento status
) {
}