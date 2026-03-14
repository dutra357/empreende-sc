package com.dutra.empreende_sc.repository;

import com.dutra.empreende_sc.entities.Empreendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpreendimentoRepository extends JpaRepository<Empreendimento, Long> {
}
