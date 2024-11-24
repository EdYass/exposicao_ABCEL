package com.abcelsystem.exposicao.repositories;

import com.abcelsystem.exposicao.entities.Avaliacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, UUID> {

    @Query("SELECT a FROM Avaliacao a " +
            "WHERE a.fichaInscricao.produto.id = :produtoId " +
            "ORDER BY (a.criterio1 + a.criterio2 + a.criterio3 + a.criterio4 + a.criterio5 + a.criterio6) DESC")
    Page<Avaliacao> findTop3ByProdutoIdOrderByNotaFinal(UUID produtoId, Pageable pageable);

    @Query("SELECT DISTINCT f.produto.id FROM Avaliacao a JOIN a.fichaInscricao f")
    List<UUID> findDistinctProdutoIds();

    List<Avaliacao> findByFichaInscricaoId(UUID fichaInscricaoId);
}
