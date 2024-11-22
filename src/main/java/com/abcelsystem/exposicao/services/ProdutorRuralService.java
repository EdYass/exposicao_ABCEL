package com.abcelsystem.exposicao.services;

import com.abcelsystem.exposicao.dtos.ProdutorRuralDTO;
import com.abcelsystem.exposicao.entities.ProdutorRural;
import com.abcelsystem.exposicao.repositories.ProdutorRuralRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutorRuralService {

    private final ProdutorRuralRepository produtorRuralRepository;

    public ProdutorRuralDTO save(ProdutorRuralDTO produtorRuralDTO) {
        ProdutorRural produtorRural = new ProdutorRural();
        BeanUtils.copyProperties(produtorRuralDTO, produtorRural);
        ProdutorRural savedProdutorRural = produtorRuralRepository.save(produtorRural);
        return convertToDTO(savedProdutorRural);
    }

    public List<ProdutorRuralDTO> findAll() {
        List<ProdutorRural> produtores = produtorRuralRepository.findAll();
        return produtores.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProdutorRuralDTO> findById(UUID id) {
        Optional<ProdutorRural> produtor = produtorRuralRepository.findById(id);
        return produtor.map(this::convertToDTO);
    }

    public ProdutorRuralDTO edit(UUID id, ProdutorRuralDTO produtorRuralDTO) {
        ProdutorRural produtorRural = produtorRuralRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produtor Rural não encontrado"));

        produtorRural.setNome(produtorRuralDTO.nome());
        produtorRural.setMunicipio(produtorRuralDTO.municipio());
        produtorRural.setBairro(produtorRuralDTO.bairro());
        produtorRural.setTelefone(produtorRuralDTO.telefone());
        produtorRural.setEmail(produtorRuralDTO.email());

        ProdutorRural updatedProdutorRural = produtorRuralRepository.save(produtorRural);
        return convertToDTO(updatedProdutorRural);
    }

    public void deleteById(UUID id) {
        produtorRuralRepository.deleteById(id);
    }

    private ProdutorRuralDTO convertToDTO(ProdutorRural produtorRural) {
        return new ProdutorRuralDTO(
                produtorRural.getId(),
                produtorRural.getNome(),
                produtorRural.getMunicipio(),
                produtorRural.getBairro(),
                produtorRural.getTelefone(),
                produtorRural.getEmail()
        );
    }
}
