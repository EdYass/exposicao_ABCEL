package com.abcelsystem.exposicao.controllers;

import com.abcelsystem.exposicao.dtos.reports.FichaInscricaoRelatorioDTO;
import com.abcelsystem.exposicao.dtos.reports.ProdutoJulgadoRelatorioDTO;
import com.abcelsystem.exposicao.dtos.reports.ProdutoRelatorioDTO;
import com.abcelsystem.exposicao.dtos.reports.ProdutorRuralRelatorioDTO;
import com.abcelsystem.exposicao.services.RelatorioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/relatorios")
@RequiredArgsConstructor
public class RelatorioController {

    private final RelatorioService relatorioService;

    @GetMapping("/produtos")
    public ResponseEntity<ProdutoRelatorioDTO> relatorioProdutosCadastrados() {
        ProdutoRelatorioDTO relatorio = relatorioService.relatorioProdutosCadastrados();
        return ResponseEntity.ok(relatorio);
    }

    @GetMapping("/produtores-cadastrados")
    public ResponseEntity<ProdutorRuralRelatorioDTO> getProdutoresCadastrados() {
        return ResponseEntity.ok(relatorioService.relatorioProdutoresCadastrados());
    }

    @GetMapping("/fichas-cadastradas")
    public ResponseEntity<FichaInscricaoRelatorioDTO> getFichasCadastradas() {
        return ResponseEntity.ok(relatorioService.relatorioFichasCadastradas());
    }

    @GetMapping("/fichas-por-produto/{produtoId}")
    public ResponseEntity<FichaInscricaoRelatorioDTO> getFichasPorProduto(@PathVariable UUID produtoId) {
        return ResponseEntity.ok(relatorioService.relatorioFichasPorProduto(produtoId));
    }

    @GetMapping("/produtos-expostos")
    public ResponseEntity<List<ProdutoJulgadoRelatorioDTO>> getProdutosJulgados() {
        List<ProdutoJulgadoRelatorioDTO> relatorio = relatorioService.relatorioIndicadoresProdutosExpostos();
        return ResponseEntity.ok(relatorio);
    }
}
