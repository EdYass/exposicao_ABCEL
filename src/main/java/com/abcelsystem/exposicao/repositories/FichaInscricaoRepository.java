package com.abcelsystem.exposicao.repositories;

import com.abcelsystem.exposicao.entities.FichaInscricao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FichaInscricaoRepository extends JpaRepository<FichaInscricao, UUID> {
    long countByProdutoId(UUID produtoId);
    List<FichaInscricao> findByProdutoId(UUID produtoId);
}
