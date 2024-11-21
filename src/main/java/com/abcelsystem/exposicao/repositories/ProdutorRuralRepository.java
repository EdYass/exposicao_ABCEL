package com.abcelsystem.exposicao.repositories;

import com.abcelsystem.exposicao.entities.ProdutorRural;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProdutorRuralRepository extends JpaRepository<ProdutorRural, UUID> {
}
