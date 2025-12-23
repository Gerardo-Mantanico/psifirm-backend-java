package com.pifirm.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "historial_personal")
@Data
public class HistorialPersonalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "hcId")
    private Long hcId;

    @Column(name = "desarrollo_evolutivo", columnDefinition = "TEXT")
    private String desarrolloEvolutivo;

    @Column(name = "h_academica", columnDefinition = "TEXT")
    private String hAcademica;

    @Column(name = "h_medica", columnDefinition = "TEXT")
    private String hMedica;

    @Column(name = "medicacion_actual", columnDefinition = "TEXT")
    private String medicacionActual;

    @Column(name = "consumo_alcohol", length = 20)
    private String consumoAlcohol;

    @Column(name = "consumo_tabaco", length = 20)
    private String consumoTabaco;

    @Column(name = "consumo_drogras", columnDefinition = "TEXT")
    private String consumoDrogras;

    @Column(name = "tratamientos_previos")
    private Boolean tratamientosPrevios;

    @Column(name = "tratamientos_previos_detalles", columnDefinition = "TEXT")
    private String tratamientosPreviosDetalles;

    @Column(name = "Hospitalizaciones")
    private Boolean hospitalizaciones;

    @Column(name = "Hospitalizaciones_detalles", columnDefinition = "TEXT")
    private String hospitalizacionesDetalles;
}

