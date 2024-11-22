package com.abcelsystem.exposicao.repositories;

import com.abcelsystem.exposicao.entities.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, UUID> {
    List<Avaliacao> findByFichaInscricaoId(UUID fichaInscricaoId);
}
