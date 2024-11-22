package com.abcelsystem.exposicao.dtos;

import com.abcelsystem.exposicao.entities.TipoCultivo;

import java.util.UUID;

public record FichaInscricaoDetalhadaDTO(
        UUID id,
        String numeroInscricao,
        TipoCultivo tipoCultivo,
        ProdutorRuralDTO produtorRural,
        ProdutoDTO produto
) {
}
