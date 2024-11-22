package com.abcelsystem.exposicao.dtos;

import java.util.UUID;

public record AvaliacaoDTO(
        UUID id,
        UUID juizId,
        UUID fichaInscricaoId,
        Double criterio1,
        Double criterio2,
        Double criterio3,
        Double criterio4,
        Double criterio5,
        Double criterio6,
        Double notaFinal
) {}
