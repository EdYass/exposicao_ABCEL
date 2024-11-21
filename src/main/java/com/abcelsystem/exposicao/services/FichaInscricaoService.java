package com.abcelsystem.exposicao.services;

import com.abcelsystem.exposicao.dtos.FichaInscricaoDTO;
import com.abcelsystem.exposicao.entities.FichaInscricao;
import com.abcelsystem.exposicao.repositories.FichaInscricaoRepository;
import com.abcelsystem.exposicao.repositories.ProdutorRuralRepository;
import com.abcelsystem.exposicao.repositories.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FichaInscricaoService {

    private final FichaInscricaoRepository fichaInscricaoRepository;
    private final ProdutorRuralRepository produtorRuralRepository;
    private final ProdutoRepository produtoRepository;

    public List<FichaInscricaoDTO> findAll() {
        List<FichaInscricao> fichas = fichaInscricaoRepository.findAll();
        return fichas.stream()
                .map(ficha -> new FichaInscricaoDTO(
                        ficha.getId(),
                        ficha.getNumeroInscricao(),
                        ficha.getTipoCultivo(),
                        ficha.getProdutorRural().getId(),
                        ficha.getProduto().getId()))
                .collect(Collectors.toList());
    }

    public Optional<FichaInscricaoDTO> findById(UUID id) {
        Optional<FichaInscricao> ficha = fichaInscricaoRepository.findById(id);
        return ficha.map(f -> new FichaInscricaoDTO(
                f.getId(),
                f.getNumeroInscricao(),
                f.getTipoCultivo(),
                f.getProdutorRural().getId(),
                f.getProduto().getId()));
    }

    public FichaInscricaoDTO save(FichaInscricaoDTO fichaInscricaoDTO) {
        FichaInscricao fichaInscricao = new FichaInscricao();
        BeanUtils.copyProperties(fichaInscricaoDTO, fichaInscricao);
        fichaInscricao.setProdutorRural(produtorRuralRepository.findById(fichaInscricaoDTO.produtorRuralId()).orElseThrow(() -> new RuntimeException("Produtor Rural não encontrado")));
        fichaInscricao.setProduto(produtoRepository.findById(fichaInscricaoDTO.produtoId()).orElseThrow(() -> new RuntimeException("Produto não encontrado")));

        FichaInscricao savedFicha = fichaInscricaoRepository.save(fichaInscricao);
        return new FichaInscricaoDTO(
                savedFicha.getId(),
                savedFicha.getNumeroInscricao(),
                savedFicha.getTipoCultivo(),
                savedFicha.getProdutorRural().getId(),
                savedFicha.getProduto().getId());
    }

    public void deleteById(UUID id) {
        fichaInscricaoRepository.deleteById(id);
    }
}
