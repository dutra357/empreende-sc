package com.dutra.empreende_sc.entities;

import com.dutra.empreende_sc.entities.enums.SegmentoAtuacao;
import com.dutra.empreende_sc.entities.enums.StatusEmpreendimento;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "empreendimentos")
public class Empreendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeEmpreendimento;

    @Column(nullable = false)
    private String nomeEmpreendedor;

    @Column(nullable = false)
    private String municipio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SegmentoAtuacao segmento;

    @Column(nullable = false)
    private String contato;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusEmpreendimento status;

    public Empreendimento() {
    }

    public Empreendimento(String nomeEmpreendimento, String nomeEmpreendedor,
                          String municipio, SegmentoAtuacao segmento, String contato,
                          StatusEmpreendimento status) {
        this.nomeEmpreendimento = nomeEmpreendimento;
        this.nomeEmpreendedor = nomeEmpreendedor;
        this.municipio = municipio;
        this.segmento = segmento;
        this.contato = contato;
        this.status = status;
    }

    public Long id() {
        return id;
    }

    public String nomeEmpreendimento() {
        return nomeEmpreendimento;
    }

    public Empreendimento setNomeEmpreendimento(String nomeEmpreendimento) {
        this.nomeEmpreendimento = nomeEmpreendimento;
        return this;
    }

    public String nomeEmpreendedor() {
        return nomeEmpreendedor;
    }

    public Empreendimento setNomeEmpreendedor(String nomeEmpreendedor) {
        this.nomeEmpreendedor = nomeEmpreendedor;
        return this;
    }

    public String municipio() {
        return municipio;
    }

    public Empreendimento setMunicipio(String municipio) {
        this.municipio = municipio;
        return this;
    }

    public SegmentoAtuacao segmento() {
        return segmento;
    }

    public Empreendimento setSegmento(SegmentoAtuacao segmento) {
        this.segmento = segmento;
        return this;
    }

    public String contato() {
        return contato;
    }

    public Empreendimento setContato(String contato) {
        this.contato = contato;
        return this;
    }

    public StatusEmpreendimento status() {
        return status;
    }

    public Empreendimento setStatus(StatusEmpreendimento status) {
        this.status = status;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Empreendimento that = (Empreendimento) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Empreendimento{" +
                "id=" + id +
                ", nomeEmpreendimento='" + nomeEmpreendimento + '\'' +
                ", nomeEmpreendedor='" + nomeEmpreendedor + '\'' +
                ", municipio='" + municipio + '\'' +
                ", segmento=" + segmento +
                ", contato='" + contato + '\'' +
                ", status=" + status +
                '}';
    }
}