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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hc_id", nullable = false)
    private HistoriaClinicaEntity historiaClinica;

    @NotNull
    @Column(name = "estructura_fa", nullable = false, columnDefinition = "TEXT")
    private String estructuraFa;

    @Column(name = "trastornos")
    private Boolean trastornos;

    @Column(name = "trastorno_detalles", columnDefinition = "TEXT")
    private String trastornoDetalles;

    @NotNull
    @Column(name = "eventos_fr", nullable = false, columnDefinition = "TEXT")
    private String eventosFr;
}

