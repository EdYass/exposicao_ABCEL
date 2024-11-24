package com.abcelsystem.exposicao.dtos.details;

import java.util.UUID;

public record TopAvaliacaoDTO(
        UUID fichaInscricaoId,
        String numeroInscricao,
        Double mediaNotaFinal
) {
}
