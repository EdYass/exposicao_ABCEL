package com.abcelsystem.exposicao.controllers;

import com.abcelsystem.exposicao.dtos.ProdutorRuralDTO;
import com.abcelsystem.exposicao.services.ProdutorRuralService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/produtores")
@RequiredArgsConstructor
public class ProdutorRuralController {

    private final ProdutorRuralService produtorRuralService;

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody ProdutorRuralDTO produtorRuralDTO) {
        ProdutorRuralDTO savedProdutor = produtorRuralService.save(produtorRuralDTO);
        return ResponseEntity.status(201).body("Produtor Rural Cadastrado com Sucesso! ID: " + savedProdutor.id());
    }

    @GetMapping
    public List<ProdutorRuralDTO> getAll() {
        return produtorRuralService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutorRuralDTO> getById(@PathVariable UUID id) {
        return produtorRuralService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> edit(@PathVariable UUID id, @Valid @RequestBody ProdutorRuralDTO produtorRuralDTO) {
        ProdutorRuralDTO updatedProdutor = produtorRuralService.edit(id, produtorRuralDTO);
        return ResponseEntity.ok("Produtor Rural Editado com Sucesso! ID: " + updatedProdutor.id());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        produtorRuralService.deleteById(id);
        return ResponseEntity.noContent().header("Message", "Produtor Rural Deletado com Sucesso!").build();
    }
}