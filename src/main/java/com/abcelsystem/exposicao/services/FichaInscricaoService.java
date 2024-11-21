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

    public FichaInscricaoDTO save(FichaInscricaoDTO fichaInscricaoDTO) {
        FichaInscricao fichaInscricao = new FichaInscricao();
        BeanUtils.copyProperties(fichaInscricaoDTO, fichaInscricao);
        fichaInscricao.setProdutorRural(produtorRuralRepository.findById(fichaInscricaoDTO.produtorRuralId())
                .orElseThrow(() -> new RuntimeException("Produtor Rural não encontrado")));
        fichaInscricao.setProduto(produtoRepository.findById(fichaInscricaoDTO.produtoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado")));

        FichaInscricao savedFicha = fichaInscricaoRepository.save(fichaInscricao);
        return convertToDTO(savedFicha);
    }

    public List<FichaInscricaoDTO> findAll() {
        List<FichaInscricao> fichas = fichaInscricaoRepository.findAll();
        return fichas.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<FichaInscricaoDTO> findById(UUID id) {
        Optional<FichaInscricao> ficha = fichaInscricaoRepository.findById(id);
        return ficha.map(this::convertToDTO);
    }

    public FichaInscricaoDTO edit(UUID id, FichaInscricaoDTO fichaInscricaoDTO) {
        FichaInscricao fichaInscricao = fichaInscricaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ficha de Inscrição não encontrada"));

        fichaInscricao.setTipoCultivo(fichaInscricaoDTO.tipoCultivo());

        fichaInscricao.setProdutorRural(produtorRuralRepository.findById(fichaInscricaoDTO.produtorRuralId())
                .orElseThrow(() -> new RuntimeException("Produtor Rural não encontrado")));
        fichaInscricao.setProduto(produtoRepository.findById(fichaInscricaoDTO.produtoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado")));

        FichaInscricao savedFicha = fichaInscricaoRepository.save(fichaInscricao);
        return convertToDTO(savedFicha);
    }

    public void deleteById(UUID id) {
        fichaInscricaoRepository.deleteById(id);
    }

    private FichaInscricaoDTO convertToDTO(FichaInscricao fichaInscricao) {
        return new FichaInscricaoDTO(
                fichaInscricao.getId(),
                fichaInscricao.getNumeroInscricao(),
                fichaInscricao.getTipoCultivo(),
                fichaInscricao.getProdutorRural().getId(),
                fichaInscricao.getProduto().getId()
        );
    }

}
