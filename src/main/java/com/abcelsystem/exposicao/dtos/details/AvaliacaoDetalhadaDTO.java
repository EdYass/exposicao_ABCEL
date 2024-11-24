package com.abcelsystem.exposicao.dtos.details;

public record AvaliacaoDetalhadaDTO(
        String id,
        String numeroInscricao,
        String nomeJuiz,
        Double criterio1,
        Double criterio2,
        Double criterio3,
        Double criterio4,
        Double criterio5,
        Double criterio6,
        Double notaFinal
) {}
