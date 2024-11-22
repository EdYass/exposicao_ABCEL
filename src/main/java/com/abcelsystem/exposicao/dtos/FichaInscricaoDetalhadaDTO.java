package com.abcelsystem.exposicao.dtos;

import com.abcelsystem.exposicao.entities.TipoCultivo;

public record FichaInscricaoDetalhadaDTO(
        String numeroInscricao,
        TipoCultivo tipoCultivo,
        ProdutorRuralDTO produtorRural,
        ProdutoDTO produto
) {
}
