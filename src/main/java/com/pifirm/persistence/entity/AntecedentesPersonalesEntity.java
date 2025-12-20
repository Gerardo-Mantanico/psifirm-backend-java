package com.pifirm.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "antecedentes_personales")
@Data
public class AntecedentesPersonalesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hc_id")
    private Long hcId;

    @NotNull
    @Column(name = "estructura_fa", nullable = false, columnDefinition = "TEXT")
    private String estructuraFa;

    @Column(name = "trastornos")
    private Boolean trastornos = false;

    @Column(name = "trastorno_detalles", columnDefinition = "TEXT")
    private String trastornoDetalles;

    @NotNull
    @Column(name = "eventos_fr", nullable = false, columnDefinition = "TEXT")
    private String eventosFr;
}

