package com.abcelsystem.exposicao.dtos;

import com.abcelsystem.exposicao.entities.TipoCultivo;
import java.util.UUID;

public record FichaInscricaoDTO(
        String numeroInscricao,
        TipoCultivo tipoCultivo,
        UUID produtorRuralId,
        UUID produtoId
) {
}
