package com.abcelsystem.exposicao.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "juiz_id", nullable = false)
    private Juiz juiz;

    @ManyToOne
    @JoinColumn(name = "ficha_inscricao_id", nullable = false)
    private FichaInscricao fichaInscricao;

    @NotNull(message = "O critério 1 não pode ser nulo")
    @Column(nullable = false)
    private Double criterio1;

    @NotNull(message = "O critério 2 não pode ser nulo")
    @Column(nullable = false)
    private Double criterio2;

    @NotNull(message = "O critério 3 não pode ser nulo")
    @Column(nullable = false)
    private Double criterio3;

    @NotNull(message = "O critério 4 não pode ser nulo")
    @Column(nullable = false)
    private Double criterio4;

    @NotNull(message = "O critério 5 não pode ser nulo")
    @Column(nullable = false)
    private Double criterio5;

    @NotNull(message = "O critério 6 não pode ser nulo")
    @Column(nullable = false)
    private Double criterio6;

    @Transient
    public Double getNotaFinal() {
        return (criterio1 + criterio2 + criterio3 + criterio4 + criterio5 + criterio6) / 6;
    }
}
