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
public class ProdutorRural {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    @NotBlank(message = "O município é obrigatório.")
    private String municipio;

    @NotBlank(message = "O bairro é obrigatório.")
    private String bairro;

    @Pattern(regexp = "\\d{10,11}", message = "O telefone deve ter entre 10 e 11 dígitos.")
    private String telefone;

    @Email(message = "E-mail inválido.")
    private String email;

    @OneToMany(mappedBy = "produtorRural", cascade = CascadeType.ALL)
    private List<FichaInscricao> fichasInscricao;
}