package com.abcelsystem.exposicao.dtos;

import java.util.UUID;

public record ProdutoDTO(
        UUID id,
        String nome,
        String variedade) {

}
