package com.abcelsystem.exposicao.controllers;

import com.abcelsystem.exposicao.dtos.FichaInscricaoDTO;
import com.abcelsystem.exposicao.dtos.details.FichaInscricaoDetalhadaDTO;
import com.abcelsystem.exposicao.services.FichaInscricaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/fichas")
@RequiredArgsConstructor
public class FichaInscricaoController {

    private final FichaInscricaoService fichaInscricaoService;

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody FichaInscricaoDTO fichaInscricaoDTO) {
        FichaInscricaoDTO savedFicha = fichaInscricaoService.save(fichaInscricaoDTO);
        return ResponseEntity.status(201).body("Ficha de Inscrição Cadastrada com Sucesso! ID: " + savedFicha.id());
    }

    @GetMapping
    public List<FichaInscricaoDTO> getAll() {
        return fichaInscricaoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FichaInscricaoDTO> getById(@PathVariable UUID id) {
        return fichaInscricaoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/detalhadas")
    public List<FichaInscricaoDetalhadaDTO> getAllDetail() {
        return fichaInscricaoService.findAllDetail();
    }

    @GetMapping("/detalhada/{id}")
    public ResponseEntity<FichaInscricaoDetalhadaDTO> getByIdDetail(@PathVariable UUID id) {
        return fichaInscricaoService.findByIdDetail(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> edit(@PathVariable UUID id, @Valid @RequestBody FichaInscricaoDTO fichaInscricaoDTO) {
        FichaInscricaoDTO updatedFicha = fichaInscricaoService.edit(id, fichaInscricaoDTO);
        return ResponseEntity.ok("Ficha de Inscrição Editada com Sucesso! ID: " + updatedFicha.id());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        fichaInscricaoService.deleteById(id);
        return ResponseEntity.noContent().header("Message", "Ficha de Tncrição Deletada com Sucesso!").build();
    }
}