package com.abcelsystem.exposicao.dtos.details;

import com.abcelsystem.exposicao.dtos.ProdutoDTO;
import com.abcelsystem.exposicao.dtos.ProdutorRuralDTO;
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
