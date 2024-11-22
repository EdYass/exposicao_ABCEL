package com.abcelsystem.exposicao.controllers;

import com.abcelsystem.exposicao.dtos.AvaliacaoDTO;
import com.abcelsystem.exposicao.dtos.AvaliacaoDetalhadaDTO;
import com.abcelsystem.exposicao.services.AvaliacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/avaliacoes")
@RequiredArgsConstructor
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody AvaliacaoDTO avaliacaoDTO) {
        AvaliacaoDTO savedAvaliacao = avaliacaoService.save(avaliacaoDTO);
        return ResponseEntity.status(201).body("Avaliação Cadastrada com Sucesso! ID: " + savedAvaliacao.id());
    }

    @GetMapping
    public List<AvaliacaoDTO> getAll() {
        return avaliacaoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoDTO> getById(@PathVariable UUID id) {
        return avaliacaoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/detalhadas")
    public ResponseEntity<List<AvaliacaoDetalhadaDTO>> getAllDetalhado() {
        List<AvaliacaoDetalhadaDTO> avaliacoesDetalhadas = avaliacaoService.findAllDetail();
        return ResponseEntity.ok(avaliacoesDetalhadas);
    }

    @GetMapping("/detalhada/{id}")
    public ResponseEntity<AvaliacaoDetalhadaDTO> getByIdDetalhado(@PathVariable UUID id) {
        return avaliacaoService.findByIdDetail(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> edit(@PathVariable UUID id, @Valid @RequestBody AvaliacaoDTO avaliacaoDTO) {
        AvaliacaoDTO updatedAvaliacao = avaliacaoService.edit(id, avaliacaoDTO);
        return ResponseEntity.ok("Avaliação Editada com Sucesso! ID: " + updatedAvaliacao.id());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        avaliacaoService.deleteById(id);
        return ResponseEntity.noContent().header("Message", "Avaliação Deletada com Sucesso!").build();
    }
}
