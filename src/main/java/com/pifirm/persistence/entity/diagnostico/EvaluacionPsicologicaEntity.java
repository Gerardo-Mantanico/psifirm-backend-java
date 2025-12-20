package com.pifirm.persistence.entity.diagnostico;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "evaluacion_psicologica")
public class EvaluacionPsicologicaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hc_id", nullable = false)
    private Long hcId;

    @Column(name = "estado_animo", nullable = false)
    private Integer estadoAnimo;

    @Column(nullable = false)
    private Integer ansiedad;

    @Column(name = "funcionamiento_social", nullable = false)
    private Integer funcionamientoSocial;

    @Column(name = "calidad_sueno", nullable = false)
    private Integer calidadSueno;

    @Column(nullable = false)
    private Integer apetito;

    @Column(name = "observaciones_generales", columnDefinition = "TEXT")
    private String observacionesGenerales;

}
