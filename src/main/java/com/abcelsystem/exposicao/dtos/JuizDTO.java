package com.abcelsystem.exposicao.dtos;

import java.util.UUID;

public record JuizDTO(
        UUID id,
        String nome,
        String email,
        String senha
) {}
