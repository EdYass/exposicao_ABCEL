package com.abcelsystem.exposicao.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "O nome do produto é obrigatório.")
    private String nome;

    @NotBlank(message = "A variedade do produto é obrigatória.")
    private String variedade;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<FichaInscricao> fichasInscricao;
}
