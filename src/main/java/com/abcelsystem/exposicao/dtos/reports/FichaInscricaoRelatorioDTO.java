package com.abcelsystem.exposicao.dtos.reports;

import java.util.List;

public record FichaInscricaoRelatorioDTO(long total, List<FichaInfo> fichas) {
    public record FichaInfo(String numeroInscricao, String produtor) {}
}
