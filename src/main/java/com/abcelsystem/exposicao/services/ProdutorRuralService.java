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

    public List<ProdutorRuralDTO> findAll() {
        List<ProdutorRural> produtores = produtorRuralRepository.findAll();
        return produtores.stream()
                .map(produtor -> new ProdutorRuralDTO(produtor.getId(), produtor.getNome(), produtor.getMunicipio(), produtor.getBairro(), produtor.getTelefone(), produtor.getEmail()))
                .collect(Collectors.toList());
    }

    public Optional<ProdutorRuralDTO> findById(UUID id) {
        Optional<ProdutorRural> produtor = produtorRuralRepository.findById(id);
        return produtor.map(p -> new ProdutorRuralDTO(p.getId(), p.getNome(), p.getMunicipio(), p.getBairro(), p.getTelefone(), p.getEmail()));
    }

    public ProdutorRuralDTO save(ProdutorRuralDTO produtorRuralDTO) {
        ProdutorRural produtorRural = new ProdutorRural();
        BeanUtils.copyProperties(produtorRuralDTO, produtorRural);
        ProdutorRural savedProdutorRural = produtorRuralRepository.save(produtorRural);
        return new ProdutorRuralDTO(savedProdutorRural.getId(), savedProdutorRural.getNome(), savedProdutorRural.getMunicipio(), savedProdutorRural.getBairro(), savedProdutorRural.getTelefone(), savedProdutorRural.getEmail());
    }

    public void deleteById(UUID id) {
        produtorRuralRepository.deleteById(id);
    }
}
