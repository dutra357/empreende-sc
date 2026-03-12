package com.dutra.empreende_sc.entities.enums;

public enum SegmentoAtuacao {
    TECNOLOGIA("Tecnologia"),
    COMERCIO("Comércio"),
    INDUSTRIA("Indústria"),
    SERVICOS("Serviços"),
    AGRONEGOCIO("Agronegócio");

    private String descricao;

    SegmentoAtuacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
