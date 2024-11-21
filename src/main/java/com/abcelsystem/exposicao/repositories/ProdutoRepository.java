package com.abcelsystem.exposicao.repositories;

import com.abcelsystem.exposicao.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<Produto, UUID>{
}