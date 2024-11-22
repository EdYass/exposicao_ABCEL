package com.abcelsystem.exposicao.repositories;

import com.abcelsystem.exposicao.entities.FichaInscricao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface FichaInscricaoRepository extends JpaRepository<FichaInscricao, UUID> {
}
