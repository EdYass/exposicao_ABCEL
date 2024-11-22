package com.abcelsystem.exposicao.controllers;

import com.abcelsystem.exposicao.dtos.JuizDTO;
import com.abcelsystem.exposicao.services.JuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/juizes")
@RequiredArgsConstructor
public class JuizController {

    private final JuizService juizService;

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody JuizDTO juizDTO) {
        JuizDTO savedJuiz = juizService.save(juizDTO);
        return ResponseEntity.status(201).body("Juiz Cadastrado com Sucesso! ID: " + savedJuiz.id());
    }

    @GetMapping
    public List<JuizDTO> getAll() {
        return juizService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JuizDTO> getById(@PathVariable UUID id) {
        return juizService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> edit(@PathVariable UUID id, @Valid @RequestBody JuizDTO juizDTO) {
        JuizDTO updatedJuiz = juizService.edit(id, juizDTO);
        return ResponseEntity.ok("Juiz Editado com Sucesso! ID: " + updatedJuiz.id());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        juizService.deleteById(id);
        return ResponseEntity.noContent().header("Message", "Juiz Deletado com Sucesso!").build();
    }
}
