package com.dutra.empreende_sc.dtos;

import com.dutra.empreende_sc.entities.enums.SegmentoAtuacao;
import com.dutra.empreende_sc.entities.enums.StatusEmpreendimento;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EmpreendimentoDtoInput(
        Long id,

        @NotBlank(message = "O nome do empreendimento é obrigatório.")
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.")
        String nomeEmpreendimento,

        @NotBlank(message = "O nome do(a) empreendedor(a) é obrigatório.")
        String nomeEmpreendedor,

        @NotBlank(message = "O município é obrigatório.")
        String municipio,

        @NotNull(message = "O segmento de atuação é obrigatório.")
        SegmentoAtuacao segmento,

        @NotBlank(message = "O e-mail ou contato é obrigatório.")
        @Email(message = "O e-mail fornecido não possui um formato válido.")
        String contato,

        @NotNull(message = "O status é obrigatório.")
        StatusEmpreendimento status
) {
}