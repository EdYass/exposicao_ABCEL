package com.abcelsystem.exposicao.services;

import com.abcelsystem.exposicao.dtos.ProdutoDTO;
import com.abcelsystem.exposicao.dtos.reports.FichaInscricaoRelatorioDTO;
import com.abcelsystem.exposicao.dtos.reports.ProdutoJulgadoRelatorioDTO;
import com.abcelsystem.exposicao.dtos.reports.ProdutoRelatorioDTO;
import com.abcelsystem.exposicao.dtos.reports.ProdutorRuralRelatorioDTO;
import com.abcelsystem.exposicao.entities.ProdutorRural;
import com.abcelsystem.exposicao.repositories.FichaInscricaoRepository;
import com.abcelsystem.exposicao.repositories.ProdutoRepository;
import com.abcelsystem.exposicao.repositories.ProdutorRuralRepository;
import com.abcelsystem.exposicao.repositories.AvaliacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RelatorioService {

    private final ProdutoRepository produtoRepository;
    private final ProdutorRuralRepository produtorRepository;
    private final FichaInscricaoRepository fichaInscricaoRepository;
    private final AvaliacaoRepository avaliacaoRepository;

    public ProdutoRelatorioDTO relatorioProdutosCadastrados() {
        List<ProdutoDTO> produtos = produtoRepository.findAll().stream()
                .map(produto -> new ProdutoDTO(
                        produto.getId(),
                        produto.getNome(),
                        produto.getVariedade()
                ))
                .collect(Collectors.toList());

        int total = produtos.size();

        return new ProdutoRelatorioDTO(total, produtos);
    }

    public ProdutorRuralRelatorioDTO relatorioProdutoresCadastrados() {
        List<String> produtores = produtorRepository.findAll()
                .stream()
                .map(ProdutorRural::getNome)
                .collect(Collectors.toList());

        return new ProdutorRuralRelatorioDTO(produtores.size(), produtores);
    }

    public FichaInscricaoRelatorioDTO relatorioFichasCadastradas() {
        List<FichaInscricaoRelatorioDTO.FichaInfo> fichas = fichaInscricaoRepository.findAll()
                .stream()
                .map(ficha -> new FichaInscricaoRelatorioDTO.FichaInfo(
                        ficha.getNumeroInscricao(),
                        ficha.getProdutorRural().getNome()
                ))
                .collect(Collectors.toList());

        return new FichaInscricaoRelatorioDTO(fichas.size(), fichas);
    }

    public FichaInscricaoRelatorioDTO relatorioFichasPorProduto(UUID produtoId) {
        List<FichaInscricaoRelatorioDTO.FichaInfo> fichas = fichaInscricaoRepository.findByProdutoId(produtoId)
                .stream()
                .map(ficha -> new FichaInscricaoRelatorioDTO.FichaInfo(
                        ficha.getNumeroInscricao(),
                        ficha.getProdutorRural().getNome()
                ))
                .collect(Collectors.toList());

        return new FichaInscricaoRelatorioDTO(fichas.size(), fichas);
    }

    public List<ProdutoJulgadoRelatorioDTO> relatorioIndicadoresProdutosExpostos() {
        List<UUID> produtosJulgados = avaliacaoRepository.findDistinctProdutoIds();

        return produtosJulgados.stream()
                .map(produtoId -> produtoRepository.findById(produtoId).map(produto ->
                                new ProdutoJulgadoRelatorioDTO(
                                        produto.getNome(),
                                        fichaInscricaoRepository.countByProdutoId(produtoId)
                                ))
                        .orElse(new ProdutoJulgadoRelatorioDTO("Produto Desconhecido", 0)))
                .collect(Collectors.toList());
    }
}
