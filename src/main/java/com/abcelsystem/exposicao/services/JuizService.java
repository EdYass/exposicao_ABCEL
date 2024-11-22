package com.abcelsystem.exposicao.services;

import com.abcelsystem.exposicao.dtos.JuizDTO;
import com.abcelsystem.exposicao.entities.Juiz;
import com.abcelsystem.exposicao.repositories.JuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JuizService {

    private final JuizRepository juizRepository;

    public JuizDTO save(JuizDTO juizDTO) {
        Juiz juiz = new Juiz();
        BeanUtils.copyProperties(juizDTO, juiz);
        Juiz savedJuiz = juizRepository.save(juiz);
        return convertToDTO(savedJuiz);
    }

    public List<JuizDTO> findAll() {
        List<Juiz> juizes = juizRepository.findAll();
        return juizes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<JuizDTO> findById(UUID id) {
        Optional<Juiz> juiz = juizRepository.findById(id);
        return juiz.map(this::convertToDTO);
    }

    public JuizDTO edit(UUID id, JuizDTO juizDTO) {
        Juiz juiz = juizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Juiz não encontrado"));

        juiz.setNome(juizDTO.nome());
        juiz.setEmail(juizDTO.email());
        juiz.setSenha(juizDTO.senha());

        Juiz updatedJuiz = juizRepository.save(juiz);
        return convertToDTO(updatedJuiz);
    }

    public void deleteById(UUID id) {
        juizRepository.deleteById(id);
    }

    private JuizDTO convertToDTO(Juiz juiz) {
        return new JuizDTO(
                juiz.getId(),
                juiz.getNome(),
                juiz.getEmail(),
                juiz.getSenha());
    }
}
