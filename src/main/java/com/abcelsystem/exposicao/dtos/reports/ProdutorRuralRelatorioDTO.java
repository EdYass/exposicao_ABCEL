package com.abcelsystem.exposicao.dtos.reports;

import com.abcelsystem.exposicao.dtos.ProdutorRuralDTO;

import java.util.List;

public record ProdutorRuralRelatorioDTO(
        long total,
        List<String> produtores
) {}
