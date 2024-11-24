package com.abcelsystem.exposicao.dtos.reports;

import com.abcelsystem.exposicao.dtos.ProdutoDTO;

import java.util.List;

public record ProdutoRelatorioDTO(
        long total,
        List<ProdutoDTO> produtos
) {}
