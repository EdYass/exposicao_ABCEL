package com.abcelsystem.exposicao.dtos;

import java.util.UUID;

public record ProdutorRuralDTO(
        UUID id,
        String nome,
        String municipio,
        String bairro,
        String telefone,
        String email
) {}
