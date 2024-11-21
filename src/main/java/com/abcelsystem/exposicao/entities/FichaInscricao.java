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
public class FichaInscricao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "O número da inscrição é obrigatório.")
    private String numeroInscricao;

    @Enumerated(EnumType.STRING)
    private TipoCultivo tipoCultivo;

    @ManyToOne
    @JoinColumn(name = "produtor_rural_id")
    private ProdutorRural produtorRural;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
}
