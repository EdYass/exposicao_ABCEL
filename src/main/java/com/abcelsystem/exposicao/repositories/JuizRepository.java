package com.abcelsystem.exposicao.repositories;

import com.abcelsystem.exposicao.entities.Juiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JuizRepository extends JpaRepository<Juiz, UUID> {}
