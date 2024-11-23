package com.abcelsystem.exposicao.services;

import com.abcelsystem.exposicao.dtos.AvaliacaoDTO;
import com.abcelsystem.exposicao.dtos.AvaliacaoDetalhadaDTO;
import com.abcelsystem.exposicao.entities.Avaliacao;
import com.abcelsystem.exposicao.repositories.AvaliacaoRepository;
import com.abcelsystem.exposicao.repositories.JuizRepository;
import com.abcelsystem.exposicao.repositories.FichaInscricaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;
    private final JuizRepository juizRepository;
    private final FichaInscricaoRepository fichaInscricaoRepository;

    public AvaliacaoDTO save(AvaliacaoDTO avaliacaoDTO) {
        Avaliacao avaliacao = new Avaliacao();
        BeanUtils.copyProperties(avaliacaoDTO, avaliacao);

        avaliacao.setJuiz(juizRepository.findById(avaliacaoDTO.juizId())
                .orElseThrow(() -> new RuntimeException("Juiz não encontrado")));
        avaliacao.setFichaInscricao(fichaInscricaoRepository.findById(avaliacaoDTO.fichaInscricaoId())
                .orElseThrow(() -> new RuntimeException("Ficha de Inscrição não encontrada")));

        Avaliacao savedAvaliacao = avaliacaoRepository.save(avaliacao);
        return convertToDTO(savedAvaliacao);
    }

    public List<AvaliacaoDTO> findAll() {
        List<Avaliacao> avaliacoes = avaliacaoRepository.findAll();
        return avaliacoes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<AvaliacaoDTO> findById(UUID id) {
        Optional<Avaliacao> avaliacao = avaliacaoRepository.findById(id);
        return avaliacao.map(this::convertToDTO);
    }

    public List<AvaliacaoDetalhadaDTO> findAllDetail() {
        List<Avaliacao> avaliacoes = avaliacaoRepository.findAll();
        return avaliacoes.stream()
                .map(this::convertToDetailedDTO)
                .collect(Collectors.toList());
    }

    public Optional<AvaliacaoDetalhadaDTO> findByIdDetail(UUID id) {
        Optional<Avaliacao> avaliacao = avaliacaoRepository.findById(id);
        return avaliacao.map(this::convertToDetailedDTO);
    }

    public List<AvaliacaoDTO> getTop3PorProduto(UUID produtoId) {
        var top3 = PageRequest.of(0, 3);
        var top3Avaliacoes = avaliacaoRepository.findTop3ByProdutoIdOrderByNotaFinal(produtoId, top3);
        return top3Avaliacoes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    public AvaliacaoDTO edit(UUID id, AvaliacaoDTO avaliacaoDTO) {
        Avaliacao avaliacao = avaliacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avaliação não encontrada"));

        avaliacao.setCriterio1(avaliacaoDTO.criterio1());
        avaliacao.setCriterio2(avaliacaoDTO.criterio2());
        avaliacao.setCriterio3(avaliacaoDTO.criterio3());
        avaliacao.setCriterio4(avaliacaoDTO.criterio4());
        avaliacao.setCriterio5(avaliacaoDTO.criterio5());
        avaliacao.setCriterio6(avaliacaoDTO.criterio6());

        avaliacao.setJuiz(juizRepository.findById(avaliacaoDTO.juizId())
                .orElseThrow(() -> new RuntimeException("Juiz não encontrado")));
        avaliacao.setFichaInscricao(fichaInscricaoRepository.findById(avaliacaoDTO.fichaInscricaoId())
                .orElseThrow(() -> new RuntimeException("Ficha de Inscrição não encontrada")));

        Avaliacao updatedAvaliacao = avaliacaoRepository.save(avaliacao);
        return convertToDTO(updatedAvaliacao);
    }

    public void deleteById(UUID id) {
        avaliacaoRepository.deleteById(id);
    }

    private AvaliacaoDTO convertToDTO(Avaliacao avaliacao) {
        return new AvaliacaoDTO(
                avaliacao.getId(),
                avaliacao.getJuiz().getId(),
                avaliacao.getFichaInscricao().getId(),
                avaliacao.getCriterio1(),
                avaliacao.getCriterio2(),
                avaliacao.getCriterio3(),
                avaliacao.getCriterio4(),
                avaliacao.getCriterio5(),
                avaliacao.getCriterio6(),
                avaliacao.getNotaFinal()
        );
    }

    private AvaliacaoDetalhadaDTO convertToDetailedDTO(Avaliacao avaliacao) {
        return new AvaliacaoDetalhadaDTO(
                avaliacao.getId().toString(),
                avaliacao.getFichaInscricao().getNumeroInscricao(),
                avaliacao.getJuiz().getNome(),
                avaliacao.getCriterio1(),
                avaliacao.getCriterio2(),
                avaliacao.getCriterio3(),
                avaliacao.getCriterio4(),
                avaliacao.getCriterio5(),
                avaliacao.getCriterio6(),
                avaliacao.getNotaFinal()
        );
    }
}
