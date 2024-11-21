package com.abcelsystem.exposicao.controllers;

import com.abcelsystem.exposicao.dtos.ProdutoDTO;
import com.abcelsystem.exposicao.services.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping
    public List<ProdutoDTO> getAll() {
        return produtoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> getById(@PathVariable UUID id) {
        return produtoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody ProdutoDTO produtoDTO) {
        ProdutoDTO savedProduto = produtoService.save(produtoDTO);
        return ResponseEntity.status(201).body("Produto Cadastrado com Sucesso! ID: " + savedProduto.id());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> edit(@PathVariable UUID id, @Valid @RequestBody ProdutoDTO produtoDTO) {
        ProdutoDTO updatedProduto = produtoService.findById(id)
                .map(produto -> produtoService.save(produtoDTO))
                .orElseThrow(() -> new RuntimeException("Produto não encontrado para edição"));

        return ResponseEntity.ok("Produto Editado com Sucesso! ID: " + updatedProduto.id());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        produtoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
